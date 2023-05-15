package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserInterface extends AppCompatActivity {

    FirebaseAuth auth;
    Button buttonLout, editProfile;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference reference;
    TextView username, age, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);
        findViewById(R.id.bt_notice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserInterface.this,DataActivity.class);
                startActivity(intent);
            }
        });
        auth = FirebaseAuth.getInstance();
        buttonLout = findViewById(R.id.logout_button);
        editProfile = findViewById(R.id.button_save);
        user = auth.getCurrentUser();
        showInfo();

        buttonLout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditProfile.class);
                intent.putExtra("username", username.getText().toString());
                intent.putExtra("age", age.getText().toString());
                intent.putExtra("name", name.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }

    public void showInfo(){
        String email = user.getEmail().replace(".",",");
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Users").child(email);

        username = findViewById(R.id.User_Username);
        age = findViewById(R.id.User_age);
        name = findViewById(R.id.User_Name);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                username.setText((CharSequence) snapshot.child("Username").getValue());
                age.setText((CharSequence) snapshot.child("Age").getValue());
                name.setText((CharSequence) snapshot.child("Name").getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}