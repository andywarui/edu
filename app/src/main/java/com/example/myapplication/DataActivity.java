package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
public class DataActivity extends AppCompatActivity {
    private List<Data> list;
    private int index = 0;
    private Timer timer = new Timer();
    private TimerTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        readData();
    }

    private void readData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream is = null;
                try {
                    is = getAssets().open("data.json", Context.MODE_PRIVATE);
                    int length = is.available();
                    byte[] buffer = new byte[length];
                    is.read(buffer);
                    Reader response = new StringReader(new String(buffer));
                    Gson gson = new Gson();
                    list = gson.fromJson(response, new TypeToken<List<Data>>() {
                    }.getType());
                    Log.i("show", "----listSize----" + list.size());
                    if (list != null && list.size() > 0) {
                        final Random random = new Random();
                        task = new TimerTask() {
                            @Override
                            public void run() {
                                index = random.nextInt(list.size() -1);
                                if (index < list.size()) {
                                    Message message = new Message();
                                    message.obj = list.get(index);
                                    handler.sendMessage(message);
                                }

                            }
                        };
                        timer.schedule(task, 1000, 1500);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("show", "----Exception----" + e.getMessage());
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Data data = (Data) msg.obj;
            Toast.makeText(DataActivity.this,
                    "StudentID:  "+data.getStudentID()+"  "+
                            "StudentName:  "+   data.getStudentName()+"  "+
                            "CourseID:  "+   data.getCourseID()+"  "+
                            "CourseName:  "+   data.getCourseName()+"  "+
                            "LecturerName:  "+   data.getLecturerName()+"  "+
                            "Lecturer Posts Lecture Notes:  "+   data.getIsLecturerPostsLectureNotes()+"  "+
                            "Assignments Due:  "+   data.getAssignmentsDue()+"  "+
                            " Assessment Submission:  "+   data.getIsAssessmentSubmission()+"  "+
                            "Student Signed Up Information:  "+   data.getStudentSignedUpInformation()
                    ,
                    Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (task != null) {
            task.cancel();
            task = null;
        }
    }
}