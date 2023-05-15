package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BST tree = new BST();
    FirebaseAuth auth;
    ImageView imgHome;
    ImageView imgUser;
    ImageView imgNotice;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_interface);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        imgUser = findViewById(R.id.imageUser);
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }

        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserInterface.class);
                startActivity(intent);
            }
        });

        //-------------------------------------------------------------Insert data in tree------------------------------------------------------------------
        List<String> courseNames = readCourseNamesFromFile();

        // 将列表中的数据插入到BST中
        for (String courseName : courseNames) {
            tree.insert(courseName);
        }

        //-------------------------------------------------------------Layout elements initialize------------------------------------------------------------------
        EditText searchBar = findViewById(R.id.serach_editText3);
        ImageView searchImage = findViewById(R.id.Search_imageView_search);
        ListView searchList = findViewById(R.id.ListView1);

        searchImage.setOnClickListener(view -> {
            String course = searchBar.getText().toString();
            ArrayList<String> result = find(course, tree.root);
            if (result.isEmpty()){
                Toast.makeText(getApplicationContext(), "No searching element !!!", Toast.LENGTH_SHORT).show();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, result);
            searchList.setAdapter(adapter);

        });
    }


    //-------------------------------------------------------------方法------------------------------------------------------------------
    // 从file里面得到数据的方法
    private List<String> readCourseNamesFromFile() {
        List<String> courseNames = new ArrayList<>();

        try {
            InputStream inputStream = getAssets().open("course_names.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                courseNames.add(line);
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return courseNames;
    }

    // 从tree里面得到数据的方法
    private ArrayList<String> find (String course, Node searchTree) {
        if (course == null || searchTree == null) {
            return new ArrayList<>();
        }

        ArrayList<String> courseList = new ArrayList<>();

        if (split(searchTree.courseName)[0].equals(course)) {
            courseList.add(searchTree.courseName);
        }
        if (course.equals(searchTree.courseName)){
            courseList.add(searchTree.courseName);
        }

        courseList.addAll(find(course, searchTree.left));
        courseList.addAll(find(course, searchTree.right));

        return courseList;
    }

    // 分割courseName
    private String[] split(String courseName) {
        return courseName.split(" ");
    }

}