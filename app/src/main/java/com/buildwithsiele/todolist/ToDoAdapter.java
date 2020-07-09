package com.buildwithsiele.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.mVieholder> {
    Context context;
    ArrayList<Task> tasks;

    public ToDoAdapter(Context context, ArrayList<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public mVieholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.to_do_item, parent, false);
        return new mVieholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mVieholder holder, int position) {
        Task task = tasks.get(position);
        holder.bind(task);


    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (tasks == null) {
            Toast.makeText(context, "No ToDo list found ", Toast.LENGTH_SHORT).show();

        } else {
            count = tasks.size();
        }
        return count;
    }

    class mVieholder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvTime, tvTask;

        public mVieholder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvTask = itemView.findViewById(R.id.tvTask);
        }

        public void bind(Task task) {
            tvTitle.setText(task.title);
            tvTask.setText(task.task);
            tvTime.setText(task.time);
        }

    }
}
