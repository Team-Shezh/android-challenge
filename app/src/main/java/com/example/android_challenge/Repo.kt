package com.example.android_challenge

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject


class Repo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)


        jsonParse()
    }


    private fun jsonParse() {

        val intent = getIntent()
        val Username = intent.extras!!.getString("username")

        var textView: TextView = findViewById(R.id.textView_repo)

        val url = "https://api.github.com/users/" + Username + "/repos"
        val request = JsonArrayRequest(Request.Method.GET, url, null, { response ->
            try {

                for (i in 0 until response.length()) {
                    val repoObject: JSONObject = response.getJSONObject(i)

                    val repoName =
                        repoObject.getString("name")


                    textView.append("$repoName,\n")
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error -> error.printStackTrace() })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue?.add(request)
    }
}
