package com.example.consumeapi_juliangarrido.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.consumeapi_juliangarrido.DogsResponse
import com.example.consumeapi_juliangarrido.R

class DogsAdapter(private val dogsList: List<String>) : RecyclerView.Adapter<DogsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DogsViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
    }


    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        val item:String = dogsList[position]
        holder.render(item)
    }


    override fun getItemCount(): Int = dogsList.size
}
