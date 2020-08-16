package com.shreyanshjain.tiss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class StudentDashboard extends AppCompatActivity {

    ArrayList<String> courseID = new ArrayList<>();
    ArrayList<String> courseName = new ArrayList<>();
    ArrayList<String> UID = new ArrayList<>();
    ArrayList<String> imgURL = new ArrayList<>();
    LinearLayout heading;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference dbUser,dbDaily,dbMonthly,dbWeekly,dbXp,min;
    ProgressBar PB;
    RecyclerView CourseRV;
    TextView minTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        CourseRV = findViewById(R.id.CoursesListRV);
        PB = findViewById(R.id.progressBar);




    }
}