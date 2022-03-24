package com.example.myplants.ui.myPlants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myplants.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.plantl_list.view.*
import kotlinx.android.synthetic.main.search_results.view.*

class MyPlantsViewModel(itemView: View):
RecyclerView.ViewHolder(itemView) {



    fun bind(team: plants){
        itemView.plantName.text = team.common_name.toString()

        val downloadedUrl= team.image_url

        if (downloadedUrl.isEmpty()) {
            // Rimage.setImageResource(R.mipmap.ic_placeholder_icon);
        } else{
            Picasso.
            get().
            load(downloadedUrl).
            resize(80,80).
            into(itemView.plant_image)
        }
    }


}