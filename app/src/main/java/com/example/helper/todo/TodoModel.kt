package com.example.helper.todo;

import android.os.Parcelable;

import java.io.Serializable;

public class TodoModel implements Serializable {

    private String message;
    private boolean status;
    private String time;

    public TodoModel(String message, boolean status, String time) {
        this.message = message;
        this.status = status;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public boolean getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
