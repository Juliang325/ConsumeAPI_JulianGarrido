package com.example.consumeapi_juliangarrido

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumeapi_juliangarrido.adapter.DogsAdapter
import com.example.consumeapi_juliangarrido.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnQueryTextListener{

    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter:DogsAdapter
    private val dogsImages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svDogs.setOnQueryTextListener(this)
        initiRecyclerView()
    }

    private fun initiRecyclerView() {
        adapter= DogsAdapter(dogsImages)
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter = adapter
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call:Response<DogsResponse> = getRetrofit().create(APIService::class.java).getDogsByBreeds("$query/images")
            val puppies: DogsResponse? = call.body()
            runOnUiThread{
                if(call.isSuccessful){
                    val image:List<String> = puppies?.images ?: emptyList()
                    dogsImages.clear()
                    dogsImages.addAll(image)
                    adapter.notifyDataSetChanged()

                }else{
                    showError()
                }
            }

        }
    }

    private fun showError(){
        Toast.makeText(this,"Ups, ha ocurrido un error", Toast.LENGTH_SHORT)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()){
            searchByName(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}