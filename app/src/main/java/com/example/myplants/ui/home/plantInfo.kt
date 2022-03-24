package com.example.myplants.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.myplants.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.search_results.view.*

class plantInfo : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_info)
        lateinit var mbase : DatabaseReference
        val pic = intent.getStringExtra(searchViewHolder.searchedImage)
        val name = intent.getStringExtra(searchViewHolder.searchedName)


        val searchedImage = findViewById<ImageView>(R.id.imageView2)
        val searchedPlant = findViewById<TextView>(R.id.searched_Name)
        val submitButton =findViewById<TextView>(R.id.submit_button)
        if(name != null){
            print(name)
        }
        searchedPlant.text = name

        mbase = FirebaseDatabase.getInstance().getReference("myplants")
        val plantId = mbase.push().key
       val addplant = SearchResults(plantId.toString(),name,pic)

        if (pic.isEmpty()) {
            // Rimage.setImageResource(R.mipmap.ic_placeholder_icon);
        } else{
            Picasso.
            get().
            load(pic).
            resize(60,60).
            into(searchedImage)
        }

        submitButton.setOnClickListener {
           mbase.child(plantId!!).setValue(addplant)
        }

    }
}