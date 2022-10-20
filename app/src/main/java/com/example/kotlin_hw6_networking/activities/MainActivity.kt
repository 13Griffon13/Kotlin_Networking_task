package com.example.kotlin_hw6_networking.activities

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.example.kotlin_hw6_networking.R
import com.example.kotlin_hw6_networking.fragments.RecyclerFragment
import com.example.kotlin_hw6_networking.services.NetworkService

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var networkServiceIntent = Intent(this, NetworkService::class.java)
//        startService(networkServiceIntent)
        var fragmetn = RecyclerFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragmetn)
                //Log.d("ASD","--------------------------")
            commit()
            //Log.d("ASD","++++++++++++++++++++")
        }
       // Log.d("ASD","**********************")
    }
}