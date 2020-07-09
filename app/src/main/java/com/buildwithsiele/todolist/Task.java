package com.buildwithsiele.todolist;

class Task {
    public String title;
    public String task;
    public String time;

    public Task() {
    }

    public Task(String title, String task, String time) {
        this.title = title;
        this.task = task;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
