package com.example.myplants.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myplants.R
import com.example.myplants.ui.myPlants.plants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.search_results.view.*

class searchAdapter( val searchResults : Searchdata) : RecyclerView.Adapter<searchViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): searchViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.search_results, parent, false)
        return searchViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return searchResults.data.count()
    }

    override fun onBindViewHolder(holder: searchViewHolder, position: Int) {
        val results = searchResults.data.get(position)
        holder?.view?.searchedName.text = results.common_name

        val downloadedUrl= results.image_url

        if (downloadedUrl.isEmpty()) {
            // Rimage.setImageResource(R.mipmap.ic_placeholder_icon);
        } else{
            Picasso.
            get().
            load(downloadedUrl).
            resize(80,80).
            into(holder.view.search_image)
        }
        holder?.searchResults = results
    }
}