package com.example.kotlin_hw6_networking.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.example.kotlin_hw6_networking.Animal
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URL

class NetworkService : Service() {

    private val networkServiceBinder:NetworkServiceBinder = NetworkServiceBinder()
    var urlString:String = "https://zoo-animal-api.herokuapp.com/animals/rand/"

    var gson = Gson()
    var client: OkHttpClient = OkHttpClient();

    private fun getOkhttpRequest(sUrl: String): String? {
        var result: String? = null
        try {
            // Create URL
            val url = URL(sUrl)
            // Build request
            val request = Request.Builder().url(url).build()
            // Execute request
            val response = client.newCall(request).execute()
            result = response.body?.string()
        }
        catch(err:Error) {
            print("Error when executing get request: "+err.localizedMessage)
        }
        return result
    }

    override fun onBind(intent: Intent): IBinder {
        return networkServiceBinder
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //starting with OKhttp
        var httpThread = Thread(Runnable {
            Log.d("ASD", "okHttp thread start working")
            var jsonResponse = getOkhttpRequest(urlString+"3")
            Log.d("ASD", jsonResponse.toString())

            var animal = gson.fromJson(jsonResponse, Array<Animal>::class.java)
            Log.d("ASD", animal.toString())
        })

        httpThread.start()

        return super.onStartCommand(intent, flags, startId)
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    inner class NetworkServiceBinder: Binder(){
        fun getService(): NetworkService {
            return this@NetworkService
        }
    }

}