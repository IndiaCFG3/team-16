package com.shreyanshjain.tiss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class Otp_activity extends AppCompatActivity {
    private Button verify;
    private EditText otp;
    private String id,dept,number,verificationId;

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference dbUser;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_activity);
        progressDialog=new ProgressDialog(Otp_activity.this);
        progressDialog.setMessage("Verifying");
        mAuth=FirebaseAuth.getInstance();
        id=getIntent().getStringExtra("ID");
        verify=(Button)findViewById(R.id.button);
        otp=(EditText)findViewById(R.id.editTextNumberPassword);
        dbUser = FirebaseDatabase.getInstance().getReference().child("RegistrationIDS").child(id);
        dbUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String num = dataSnapshot.child("Mob").getValue().toString();
                String dept = dataSnapshot.child("Dept").getValue().toString();
                String number = "+91"+num;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        PhoneAuthProvider.getInstance().verifyPhoneNumber(number, 60, TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                String code = phoneAuthCredential.getSmsCode();
                if (code != null) {
                    //Code is Detected Automatically
                    otp.setText(code);
                    verifyCode(code);
                }
            }

            @Override
            public void onCodeSent(@NonNull String s,@NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationId = s;
                Log.d("verificationID:",verificationId);
            }
            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                progressDialog.dismiss();
                Toast.makeText(Otp_activity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        });
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String code=otp.getText().toString().trim();
                if(code.isEmpty()||code.length()<6){
                    otp.setError("Enter the Code ...");
                    otp.requestFocus();
                    return;
                }
                verifyCode(code);
            }
        });
    }
    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }
    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser users = FirebaseAuth.getInstance().getCurrentUser();
                            dbUser = FirebaseDatabase.getInstance().getReference().child("RegistrationIDS").child(users.getUid());

                            dbUser.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.exists()){
                                        String dept = dataSnapshot.child("dept").getValue().toString();
                                        Intent intent = new Intent(Otp_activity.this,Login_page.class);
                                        intent.putExtra("dept",dept);
                                        startActivity(intent);
                                        //startActivity(new Intent(OtpVerification.this,homePage.class));
                                        Toast.makeText(Otp_activity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        startActivity(new Intent(Otp_activity.this,Login_page.class));
                                        Toast.makeText(Otp_activity.this, "One time Registration Required", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    progressDialog.dismiss();
                                    new AlertDialog.Builder(Otp_activity.this)
                                            .setTitle("Oops!")
                                            .setMessage("Something went wrong!")
                                            .setNegativeButton("Cancel", null)
                                            .show();
                                }
                            });

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(Otp_activity.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}