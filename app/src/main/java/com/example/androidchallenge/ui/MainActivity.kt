package com.example.androidchallenge.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.androidchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.findButton.setOnClickListener {
            Log.d("main", binding.userName.text.toString().trim().replace(' ', '-').lowercase())
            if (binding.userName.text.toString().trim() != "") {
                val intent = Intent(this, RepositoryList::class.java)
                intent.putExtra("userName", binding.userName.text.toString().trim().replace(' ', '-').lowercase())
                startActivity(intent)
                finish()
            } else  {
                Toast.makeText(this, "Enter the user name", Toast.LENGTH_SHORT).show()
            }

        }
    }
}