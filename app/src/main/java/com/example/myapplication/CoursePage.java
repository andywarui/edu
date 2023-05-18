package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CoursePage extends AppCompatActivity {

    private TextView courseNameTextView;
    private TextView lecturerNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);

        // Retrieve the course details from intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String courseName = extras.getString("courseName");
            String lecturerName = extras.getString("lecturerName");

            // Update the UI with the course details
            courseNameTextView = findViewById(R.id.course_name_text_view);
            lecturerNameTextView = findViewById(R.id.lecturer_name_text_view);

            courseNameTextView.setText(courseName);
            lecturerNameTextView.setText(lecturerName);
        }
    }
}
