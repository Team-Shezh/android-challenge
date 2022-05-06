package com.example.android_challenge

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class Username : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_username)


    }

    fun getRepo(view: View) {


        var username: TextView = findViewById(R.id.username)
        val Username = username.getText().toString()
        val i = Intent(this, Repo::class.java)
        i.putExtra("username", Username)
        startActivity(i)
    }
}
