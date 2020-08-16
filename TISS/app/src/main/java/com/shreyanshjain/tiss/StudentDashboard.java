package com.shreyanshjain.tiss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentDashboard extends AppCompatActivity {

    ArrayList<String> courseID = new ArrayList<>();
    ArrayList<String> courseName = new ArrayList<>();
    ArrayList<String> imgURL = new ArrayList<>();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference db;
    ProgressBar PB;
    RecyclerView CourseRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        CourseRV = findViewById(R.id.CoursesListRV);
        PB = findViewById(R.id.progressBar);
        //db = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid().toString()).child("Courses");

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                PB.setVisibility(View.VISIBLE);
                courseID.clear();
                courseName.clear();
                imgURL.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    courseID.add(snapshot.getKey());
                    courseName.add(snapshot.child("CourseName").getValue().toString());
                    imgURL.add(snapshot.child("CourseIMG").getValue().toString());

                }
                //initdailyLeaderboard();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                PB.setVisibility(View.INVISIBLE);
                new AlertDialog.Builder(StudentDashboard.this)
                        .setTitle("Oops!")
                        .setMessage("Something went wrong!")
                        .setNegativeButton("CANCEL", null)
                        .setIcon(R.drawable.ic_alert)
                        .show();
            }
        });





    }
}