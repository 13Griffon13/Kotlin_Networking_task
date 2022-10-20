package com.example.kotlin_hw6_networking.fragments

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_hw6_networking.Animal
import com.example.kotlin_hw6_networking.R

class AnimalAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var animals:MutableList<Animal> = mutableListOf<Animal>()

    class AnimalHolder(open var view: View):RecyclerView.ViewHolder(view){

        var name:TextView
        var type:TextView
        var width:TextView
        var length:TextView
        var diet:TextView
        var lifespan:TextView
        var habitat:TextView
        var activeTime:TextView
        var geoRange:TextView
        var imageView:ImageView

        init {
            name = view.findViewById(R.id.animalName)
            type = view.findViewById(R.id.animalType)
            width = view.findViewById(R.id.animalWidth)
            length = view.findViewById(R.id.animalLength)
            diet = view.findViewById(R.id.animalDiet)
            lifespan = view.findViewById(R.id.animalLifespan)
            habitat = view.findViewById(R.id.animalHabitat)
            activeTime = view.findViewById(R.id.animalActiveTime)
            geoRange = view.findViewById(R.id.animalGeorange)
            imageView = view.findViewById(R.id.animalImage)
        }

        fun bind(animal: Animal) {
            name.text = "${animal.name} lat. ${animal.latinName}"
            type.text = animal.animalType
            width.text = "Width: from ${animal.weightMin} to ${animal.weightMax}"
            length.text = "Length: from ${animal.lengthMin} to ${animal.lengthMax}"
            diet.text = "Diet: ${animal.diet}"
            lifespan.text = "Lifespan: ${animal.lifespan}"
            habitat.text = "Habitat: ${animal.habitat}"
            activeTime.text = "Active time:${animal.activeTime}"
            geoRange.text = "Geo range is: ${animal.geoRange}"
            //imageView
        }
    }



    fun addData(newAnimals:Array<Animal>){
//        Log.d("ASD","---------------------------------------------")
//        newAnimals.forEach {
//            Log.d("ASD",it.name)
//        }
        animals.addAll(newAnimals)
    }


    fun setData(list: MutableList<Animal>) {
        animals = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.animal_card,parent, false)

        return AnimalHolder(view)
    }

    override fun getItemCount(): Int {
        return animals.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as AnimalHolder
        holder.bind(animals[position])
    }

}
