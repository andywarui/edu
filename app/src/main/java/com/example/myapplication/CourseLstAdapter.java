package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CourseLstAdapter extends ArrayAdapter<Data> {
    private Context mContext;
    int mResource;

    public CourseLstAdapter(@NonNull Context context, int resource, @NonNull List<Data> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String courseID = getItem(position).getCourseID();
        String courseName = getItem(position).getCourseName();
        String lecturerName = getItem(position).getLecturerName();
        String StudentID = getItem(position).getStudentID();
        String studentName = getItem(position).getStudentName();
        String assign_due = getItem(position).getAssignmentsDue();
        String sub_status = getItem(position).getIsAssessmentSubmission();
        String isLectur_post_notes = getItem(position).getIsLecturerPostsLectureNotes();
        String stu_signupInfo = getItem(position).getStudentSignedUpInformation();
        String submit_effect = "isAssessmentSubmission: Yes";

        Data C_info = new Data(assign_due,courseID,courseName,sub_status,isLectur_post_notes,lecturerName,
                StudentID,studentName,stu_signupInfo);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tvCourseID = (TextView) convertView.findViewById(R.id.courseID);
        TextView tvCourseName = (TextView) convertView.findViewById(R.id.courseName);
        TextView tvSubmitstatus = (TextView) convertView.findViewById(R.id.submission_status);
        TextView tvLecturerName= (TextView) convertView.findViewById(R.id.lecturerName);
        TextView tvStudentName = (TextView) convertView.findViewById(R.id.studentName);
        TextView tvStudentID = (TextView) convertView.findViewById(R.id.studentID);

        tvCourseID.setText(courseID);
        tvCourseName.setText(courseName);
        tvStudentID.setText(StudentID);
        tvLecturerName.setText(lecturerName);
        tvStudentName.setText(studentName);

        Button button = (Button) convertView.findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvSubmitstatus.getText().toString().equals("isAssessmentSubmission: No")){
                    tvSubmitstatus.setText(submit_effect);
                } else{
                    Toast.makeText(mContext,"already submitted",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }
}
