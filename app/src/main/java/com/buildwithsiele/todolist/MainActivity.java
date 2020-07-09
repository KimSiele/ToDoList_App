package com.buildwithsiele.todolist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton btAdd;
    ArrayList<Task> tasks;
    ToDoAdapter toDoAdapter;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        btAdd = findViewById(R.id.btAdd);
        getTasks();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Add Your Todo Task");
                final View dialog = getLayoutInflater().inflate(R.layout.add_todo_layout, null);
                builder.setView(dialog);
                builder.setCancelable(false);
                final EditText etTitle = dialog.findViewById(R.id.etTitle);
                final EditText etTask = dialog.findViewById(R.id.etTask);
                final TextView tvTime = dialog.findViewById(R.id.tv_time);

                long current_time = System.currentTimeMillis();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, HH:mm");
                tvTime.setText(dateFormat.format(current_time));


                builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        final String title = etTitle.getText().toString();
                        final String time = tvTime.getText().toString();
                        final String task = etTask.getText().toString();

                        if (title.isEmpty()) {
                            etTitle.setError("Title cannot be empty");
                        } else if (task.isEmpty()) {
                            etTitle.setError("Task field cannot be empty");
                        } else {
                            tasks.add(new Task(title, task, time));
                            saveTaskList(tasks);
                            toDoAdapter = new ToDoAdapter(MainActivity.this, tasks);
                            recyclerView.setAdapter(toDoAdapter);
                            toDoAdapter.notifyDataSetChanged();


                        }
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


    }

    public void saveTaskList(ArrayList<Task> tasks) {

        SharedPreferences preferences = getSharedPreferences("TaskPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(tasks);
        editor.putString("task list", json);
        editor.apply();
    }

    public void getTasks() {

        SharedPreferences preferences = getSharedPreferences("TaskPref", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(tasks);
        preferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        tasks = gson.fromJson(json, type);

        if (tasks == null) {
            tasks = new ArrayList<>();
        }
    }


}