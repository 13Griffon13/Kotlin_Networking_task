package com.example.kotlin_hw6_networking.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_hw6_networking.Animal
import com.example.kotlin_hw6_networking.Constants
import com.example.kotlin_hw6_networking.R
import com.example.kotlin_hw6_networking.services.RetrofitEndpointInterface
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class RecyclerFragment : Fragment() {

    val handler = Handler(Looper.getMainLooper())
    var gson = Gson()
    var okHttpClient: OkHttpClient = OkHttpClient()
    var animalAdapter = AnimalAdapter()

    fun performRetrofitRequest(sUrl: String):Array<Animal>? {
        //Log.d("ASD","Thread "+Thread.currentThread().name+" STARTED")
        val retrofitClient = Retrofit.Builder()
            .baseUrl(sUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var myInterface = retrofitClient.create(RetrofitEndpointInterface::class.java)
        var call = myInterface.getUser("3")
        var result = call?.execute()
        return result?.body()
    }

    private fun performOkhttpRequest(sUrl: String): Array<Animal>? {
        var result: Array<Animal>? = null
        try {
            val url = URL(sUrl)
            val request = Request.Builder().url(url).build()
            val response = okHttpClient.newCall(request).execute()

            result = gson.fromJson(response.body?.string(), Array<Animal>::class.java)
        } catch (err: Error) {
            print("Error when executing get request: " + err.localizedMessage)
        }
        return result
    }

    fun launchRequests() {
        var thread = Thread(Runnable {
            var resultList = mutableListOf<Animal>()
            var firstGroup = performOkhttpRequest(Constants.urlAddress+"6")
            Log.d("ASD","first finished")
            var secondGroup = performRetrofitRequest(Constants.urlAddress)
            Log.d("ASD","second finished")
            if (firstGroup != null){
                resultList.addAll(firstGroup)
            }
            if(secondGroup != null){
                resultList.addAll(secondGroup)
            }
            handler.post {
                animalAdapter.addData(resultList.toTypedArray())
                animalAdapter.notifyItemChanged(0)
            }
        })
        thread.start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("ASD", "Fragment on create")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.d("ASD", "Fragment on create view")
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("ASD", "Fragment on view created")
        launchRequests()
        var recycleView = view.findViewById<RecyclerView>(R.id.recycleView)
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = animalAdapter
        super.onViewCreated(view, savedInstanceState)
    }


}
