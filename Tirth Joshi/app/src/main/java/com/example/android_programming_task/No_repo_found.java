package com.example.android_programming_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class No_repo_found extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_repo_found);
    }

    public void back(View view) {
        Intent intent = new Intent(No_repo_found.this, MainActivity.class);
        startActivity(intent);
    }
}