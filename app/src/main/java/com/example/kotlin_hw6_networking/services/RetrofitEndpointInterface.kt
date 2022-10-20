package com.example.kotlin_hw6_networking.services

import com.example.kotlin_hw6_networking.Animal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface RetrofitEndpointInterface {

 @GET("{num}")
 fun getUser(@Path("num") username: String?): Call<Array<Animal>>?

}