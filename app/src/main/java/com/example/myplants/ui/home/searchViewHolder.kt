package com.example.myplants.ui.home

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.myplants.ui.myPlants.plants
import kotlinx.android.synthetic.main.search_results.view.*

class searchViewHolder(val view: View , var searchResults: SearchResults? = null): RecyclerView.ViewHolder(view) {

    companion object{
        val searchedImage = "plantImage"
        val searchedName = "plantName"
    }

    init {
        view.setOnClickListener {
           // val results = searchResults?.data?.
            val intent = Intent(view.context, plantInfo::class.java)
                 intent.putExtra(searchedImage, searchResults?.image_url )
            intent.putExtra(searchedName, searchResults?.common_name)
            view.context.startActivity(intent)
        }
    }
}






