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
import com.example.kotlin_hw6_networking.R
import com.example.kotlin_hw6_networking.services.RetrofitEndpointInterface
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class RecyclerFragment : Fragment() {

    var urlString: String = "https://zoo-animal-api.herokuapp.com/animals/rand/"
    val handler = Handler(Looper.getMainLooper())
    var gson = Gson()
    var okHttpClient: OkHttpClient = OkHttpClient()
    var animalAdapter = AnimalAdapter()

    fun performRetrofitRequest(sUrl:String){
        var httpThread = Thread(Runnable {
            //Log.d("ASD","Thread "+Thread.currentThread().name+" STARTED")
            val retrofitClient = Retrofit.Builder()
                .baseUrl(sUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            var myInterface = retrofitClient.create(RetrofitEndpointInterface::class.java)
            var call = myInterface.getUser("3")
            var result = call?.execute()
            var resultBody = result?.body()
            synchronized(animalAdapter){
                if (resultBody!= null) {
                    animalAdapter.addData(resultBody)
                    handler.post {
                        animalAdapter.notifyItemChanged(animalAdapter.itemCount)
                    }
                }
            }
        })

        httpThread.start()
    }

    private fun getOkhttpRequest(sUrl: String): String? {
        var result: String? = null
        try {
            val url = URL(sUrl)
            val request = Request.Builder().url(url).build()
            val response = okHttpClient.newCall(request).execute()
            result = response.body?.string()
        } catch (err: Error) {
            print("Error when executing get request: " + err.localizedMessage)
        }
        return result
    }

    fun performOkhhtpRequest() {
        var httpThread = Thread(Runnable {
            //Log.d("ASD", "okHttp thread start working")
            var jsonResponse = getOkhttpRequest(urlString + "6")
            //Log.d("ASD", jsonResponse.toString())

            var animals = gson.fromJson(jsonResponse, Array<Animal>::class.java)
            synchronized(animalAdapter){
                animalAdapter.addData(animals)
                handler.post {
                    animalAdapter.notifyItemChanged(animalAdapter.itemCount)
                }
            }
        })

        httpThread.start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("ASD","Fragment on create")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.d("ASD","Fragment on create view")
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("ASD","Fragment on view created")
        performOkhhtpRequest()
        performRetrofitRequest(urlString)
        var recycleView = view.findViewById<RecyclerView>(R.id.recycleView)
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = animalAdapter
        super.onViewCreated(view, savedInstanceState)
    }


}
