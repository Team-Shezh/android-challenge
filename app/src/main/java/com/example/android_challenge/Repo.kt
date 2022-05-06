package com.example.android_challenge

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
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
        val intent = getIntent()
        val Username = intent.extras!!.getString("username")

        var textView_title: TextView = findViewById(R.id.title)
        textView_title.append("$Username\n")



        jsonParse()
    }


    @RequiresApi(Build.VERSION_CODES.M)
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
        }, { error ->
            error.printStackTrace()
            if (isOnline(this)) {
                var errorCode = error.networkResponse.statusCode.toString()
                if (errorCode.equals("404")) {
                    startActivity(Intent(this, UserNotFound::class.java))
                }
            } else{
                Toast.makeText(applicationContext,"Please Turn on The internet and try again."
                ,Toast.LENGTH_LONG).show()
            }
        })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue?.add(request)
    }
}

@RequiresApi(Build.VERSION_CODES.M)
fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
    }
    return false
}