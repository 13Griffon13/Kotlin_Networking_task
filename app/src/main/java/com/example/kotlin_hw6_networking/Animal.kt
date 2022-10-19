package com.example.kotlin_hw6_networking

import com.google.gson.annotations.SerializedName

data class Animal(
    @SerializedName("name") val name: String,
    @SerializedName("latin_name") val latinName: String,
    @SerializedName("animal_type") val animalType: String,
    @SerializedName("active_time") val activeTime: String,
    @SerializedName("length_min") val lengthMin: String,
    @SerializedName("length_max") val lengthMax: String,
    @SerializedName("weight_min") val weightMin: String,
    @SerializedName("weight_max") val weightMax: String,
    @SerializedName("lifespan") val lifespan: String,
    @SerializedName("habitat") val habitat: String,
    @SerializedName("diet")  val diet: String,
    @SerializedName("geo_range") val geoRange: String,
    @SerializedName("image_link") val imageLink: String,
    @SerializedName("id") val id: Long
)