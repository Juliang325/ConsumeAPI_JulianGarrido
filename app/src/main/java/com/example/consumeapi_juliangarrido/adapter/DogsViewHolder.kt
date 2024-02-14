package com.example.consumeapi_juliangarrido.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.consumeapi_juliangarrido.DogsResponse
import com.example.consumeapi_juliangarrido.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class DogsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemDogBinding.bind(view)

    fun render(image:String){
        //Para cargar la imagen utilizo la libreria picasso
        Picasso.get().load(image).into(binding.ivDog)
    }
}
