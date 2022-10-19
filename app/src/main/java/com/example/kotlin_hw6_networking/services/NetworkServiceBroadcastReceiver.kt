package com.example.kotlin_hw6_networking.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.kotlin_hw6_networking.Animal

class NetworkServiceBroadcastReceiver : BroadcastReceiver() {

    var allDataReceived:((list:List<Animal>)->Unit)? = null

    override fun onReceive(p0: Context?, p1: Intent?) {

    }



}