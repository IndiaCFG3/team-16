package com.shreyanshjain.tiss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_page extends AppCompatActivity {
    private Button otp_btn;
    private EditText registration_id;
    ProgressDialog progressDialog;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        progressDialog=new ProgressDialog(Login_page.this);
        progressDialog.setMessage("Generating OTP");
        otp_btn=(Button)findViewById(R.id.request_otp_btn);
        registration_id=(EditText)findViewById(R.id.registration_id);
        otp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                final String a = registration_id.getText().toString();
                if(TextUtils.isEmpty(a)){
                    progressDialog.dismiss();
                    registration_id.requestFocus();
                    registration_id.setError("Please Enter a valid registration id");
                }
                else{
                    final String b = registration_id.getText().toString();
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("RegistrationIDS").child(b);
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()){
                                progressDialog.dismiss();
                                Intent intent = new Intent(Login_page.this,Otp_activity.class);
                                intent.putExtra("ID",b);
                                startActivity(intent);

                            }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(Login_page.this, "Access denied...Please contact admin for more info", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            progressDialog.dismiss();
                            Toast.makeText(Login_page.this, "Error Occured...try again", Toast.LENGTH_SHORT).show();
                            registration_id.setText("");
                        }
                    });




                }

            }
        });
    }
}