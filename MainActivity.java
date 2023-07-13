package com.kirtanpatel.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity {

        private EditText editTextTask;
        private Button buttonAdd;
        private ListView listViewTasks;
        private ArrayList<String> tasksList;
        private ArrayAdapter<String> adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Initialize views
            editTextTask = findViewById(R.id.editTextTask);
            buttonAdd = findViewById(R.id.buttonAdd);
            listViewTasks = findViewById(R.id.listViewTasks);

            // Initialize task list and adapter
            tasksList = new ArrayList<>();
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, tasksList);
            listViewTasks.setAdapter(adapter);

            // Add button click listener
            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addTask();
                }
            });

            // List item click listener
            listViewTasks.setOnItemClickListener((parent, view, position, id) -> {
                CheckedTextView checkedTextView = (CheckedTextView) view;
                checkedTextView.setChecked(!checkedTextView.isChecked());
                tasksList.set(position, checkedTextView.getText().toString());
            });

            // List item long click listener
            listViewTasks.setOnItemLongClickListener((parent, view, position, id) -> {
                tasksList.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Task removed", Toast.LENGTH_SHORT).show();
                return true;
            });
        }

        private void addTask() {
            String task = editTextTask.getText().toString().trim();

            if (!task.isEmpty()) {
                tasksList.add(task);
                adapter.notifyDataSetChanged();
                editTextTask.setText("");
            } else {
                Toast.makeText(this, "Enter a new items", Toast.LENGTH_SHORT).show();
            }
        }
    }
