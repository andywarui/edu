package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class course_info_prof extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info_prof);
        ArrayList<Data> c_infos = new ArrayList<>();
        load_data(c_infos);
        ListView lw1 = (ListView) findViewById(R.id.ListView2);
        CourseLstAdapter adapter = new CourseLstAdapter(this, R.layout.adapter_view_layout, c_infos);
        lw1.setAdapter(adapter);
    }

    private void load_data(ArrayList<Data> cInfos) {
        try {
            //load json
            InputStream inputStream = getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json;
            String assignmentsDue = "", courseID = "", courseName = "", isAssessmentSubmission = "", isLecturerPostsLectureNotes = "";
            String lecturerName = "", studentID = "", studentName = "", studentSignedUpInformation = "";
            json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray js_Array = new JSONArray(json);
            int length = js_Array.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = js_Array.getJSONObject(i);
                if (jsonObject.has("assignmentsDue")) {
                    assignmentsDue = "assignmentsDue: " + jsonObject.getString("assignmentsDue");
                }
                if (jsonObject.has("courseID")) {
                    courseID = "courseID: " + jsonObject.getString("courseID");
                }
                if (jsonObject.has("courseName")) {
                    courseName = "courseName: " + jsonObject.getString("courseName");
                }
                if (jsonObject.has("isAssessmentSubmission")) {
                    isAssessmentSubmission = "Submission_Status: " + jsonObject.getString("isAssessmentSubmission");
                }
                if (jsonObject.has("isLecturerPostsLectureNotes")) {
                    isLecturerPostsLectureNotes = "isLecturerPostsLectureNotes: " + jsonObject.getString("isLecturerPostsLectureNotes");
                }
                if (jsonObject.has("lecturerName")) {
                    lecturerName = "lecturerName: " + jsonObject.getString("lecturerName");
                }
                if (jsonObject.has("studentID")) {
                    studentID = "studentID: " + jsonObject.getString("studentID");
                }
                if (jsonObject.has("studentName")) {
                    studentName = "studentName: " + jsonObject.getString("studentName");
                }
                if (jsonObject.has("studentSignedUpInformation")) {
                    studentSignedUpInformation = "studentSignedUpInformation: " + jsonObject.getString("studentSignedUpInformation");
                }

                Data info = new Data(assignmentsDue, courseID, courseName, isAssessmentSubmission, isLecturerPostsLectureNotes, lecturerName, studentID, studentName, studentSignedUpInformation);
                cInfos.add(info);
                Log.d("TAG", "Loadjson: assigmentsDue: " + assignmentsDue + " courseID: " + courseID + " courseName: " + courseName + " isAssignmentSubmission " +
                        isAssessmentSubmission + " isLecturerPostLectureNotes " + isLecturerPostsLectureNotes + " lecturerName " + lecturerName + " studentId " + studentID +
                        " studentName " + studentName + "studentSignedUpInformation " + studentSignedUpInformation);
            }


        } catch (IOException e) {
            e.printStackTrace();
            Log.d("TAG", "LoadJson: error " + e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}