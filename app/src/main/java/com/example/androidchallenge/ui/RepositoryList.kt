package com.example.androidchallenge.ui

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.androidchallenge.R
import com.example.androidchallenge.adapter.Adapter
import com.example.androidchallenge.databinding.ActivityRepositoryListBinding
import com.example.androidchallenge.model.dataModel
import org.json.JSONException

class RepositoryList : AppCompatActivity() {
    private lateinit var binding: ActivityRepositoryListBinding
    private var dataList = ArrayList<dataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRepositoryListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val userName: String = intent.getStringExtra("userName").toString()


//        setting the app title on this screen as the searched name
        supportActionBar?.setTitle(userName.replace('-', ' ').uppercase())



        makeHttpRequest(userName)
    }

    //    this function will make the network call and add the items in ArrayList
    private fun makeHttpRequest(userName: String) {
        val adapter = Adapter(dataList, this)
        val url = "https://api.github.com/users/" + userName + "/repos"
        val queue = Volley.newRequestQueue(this)


        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

//making the http request
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { response ->

//                successfull response listener

                try {

                    for (i in 0 until response.length()) {
                        var repositoryInfo = response.getJSONObject(i)
                        var repoName = repositoryInfo.getString("name")
                        var repoDescription = repositoryInfo.getString("description")
                        var repoUrl = repositoryInfo.getString("html_url")
                        dataList.add(dataModel(repoName, repoUrl, repoDescription))
                    }

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                adapter.notifyDataSetChanged()

            },
            { error ->

//                error listener

                AlertDialog.Builder(this)
                    .setTitle("User Not Found !!")
                    .setIcon(R.drawable.ic_baseline_error)
                    .setMessage("Enter the user name again.").setPositiveButton(
                        "ok",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            finish()
                            dialogInterface.dismiss()
                            startActivity(Intent(this, MainActivity::class.java))
                        })
                    .show()

            }
        )
        queue?.add(jsonArrayRequest)

    }
}