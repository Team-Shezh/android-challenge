package com.example.android_programming_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText UserName;

    public static final String EXTRA_TEXT = "com.example.android_programming_task.EXTRA_TEXT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserName = findViewById(R.id.editTextTextUsername);


    }

    public void generate(View view) {
        String username = UserName.getText().toString();

        Intent intent = new Intent(this,repo_summary.class);
        intent.putExtra(EXTRA_TEXT,username);
        startActivity(intent);
    }
}