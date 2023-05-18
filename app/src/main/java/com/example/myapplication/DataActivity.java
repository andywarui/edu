package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {
    private List<Data> list;
    private int index = 0;
    private Timer timer = new Timer();
    private TimerTask task;
    private CourseLstAdapter adapter;
    private final List<Data> followedCourses = new ArrayList<>(); // List to store followed courses

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
                                index = random.nextInt(list.size() - 1);
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
                    "StudentID: " + data.getStudentID() + "  " +
                            "StudentName: " + data.getStudentName() + "  " +
                            "CourseID: " + data.getCourseID() + "  " +
                            "CourseName: " + data.getCourseName() + "  " +
                            "LecturerName: " + data.getLecturerName() + "  " +
                            "Lecturer Posts Lecture Notes: " + data.getIsLecturerPostsLectureNotes() + "  " +
                            "Assignments Due: " + data.getAssignmentsDue() + "  " +
                            " Assessment Submission: " + data.getIsAssessmentSubmission() + "  " +
                            "Student Signed Up Information: " + data.getStudentSignedUpInformation(),
                    Toast.LENGTH_SHORT).show();
        }
    };

    private void showFollowedUpdates() {
        // Display the updates from all followed courses/items in chronological order
        // You can iterate over the 'followedCourses' list and retrieve the updates for each course/item
        // Display the updates in the desired format (e.g., in a separate section or as a list)

        // Example code to display the followed course names
        StringBuilder updatesBuilder = new StringBuilder();
        for (Data course : followedCourses) {
            updatesBuilder.append(course.getCourseName()).append("\n");
        }

        // Show the updates to the user (e.g., in a TextView or Toast)
        String updatesText = updatesBuilder.toString();
        Toast.makeText(this, "Followed Updates:\n" + updatesText, Toast.LENGTH_LONG).show();
    }

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
