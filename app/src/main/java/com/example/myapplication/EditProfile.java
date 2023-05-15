package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends AppCompatActivity {

    EditText editUsername, editAge, editName;
    FirebaseAuth auth;
    FirebaseUser user;
    Button save;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editUsername = findViewById(R.id.User_Username);
        editAge = findViewById(R.id.User_age);
        editName = findViewById(R.id.User_Name);
        save = findViewById(R.id.button_save);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String age = intent.getStringExtra("age");
        String name = intent.getStringExtra("name");

        editUsername.setText(username);
        editAge.setText(age);
        editName.setText(name);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeDBInfo();
                Toast.makeText(getApplicationContext(),"Info has been changed", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplicationContext(),UserInterface.class);
                startActivity(intent1);
                finish();

            }
        });



    }

    public void ChangeDBInfo() {
        String username = editUsername.getText().toString();

        if(username.contains(".") || username.contains("$") || username.contains("#") || username.contains("\\") || username.contains("/")){
            username = username.replaceAll("[.$#\\/]", "_");
            Toast.makeText(getApplicationContext(),"Invalid Character (.$#\\/) has been replaced by _ ", Toast.LENGTH_SHORT).show();
        }

        String age = editAge.getText().toString();
        String name = editName.getText().toString();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        String email = user.getEmail().replace(".",",");

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Users").child(email);

        reference.child("Username").setValue(username);
        reference.child("Age").setValue(age);
        reference.child("Name").setValue(name);

    }

}