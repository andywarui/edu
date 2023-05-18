package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class TodoListActivity extends AppCompatActivity {

    private ArrayList<String> todoItems;
    private ArrayAdapter<String> todoAdapter;
    private EditText todoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        todoItems = new ArrayList<>();
        todoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todoItems);

        todoEditText = findViewById(R.id.todo_edit_text);
        Button addButton = findViewById(R.id.add_button);
        ListView todoListView = findViewById(R.id.todo_list_view);

        todoListView.setAdapter(todoAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTodo = todoEditText.getText().toString().trim();
                if (!newTodo.isEmpty()) {
                    todoItems.add(newTodo);
                    todoAdapter.notifyDataSetChanged();
                    todoEditText.setText("");
                }
            }
        });
    }
}
