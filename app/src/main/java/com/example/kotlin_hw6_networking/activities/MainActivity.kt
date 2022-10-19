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

    var networkService:NetworkService? = null
    var networkServiceConnection:ServiceConnection? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var networkServiceIntent = Intent(this, NetworkService::class.java)
        startService(networkServiceIntent)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, RecyclerFragment())
            commit()
        }
    }
}