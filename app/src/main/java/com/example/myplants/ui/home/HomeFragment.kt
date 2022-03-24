package com.example.myplants.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.SearchEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myplants.R
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.internal.http.promisesBody
import java.io.IOException
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    val client = OkHttpClient()
    val url = "https://trefle.io/api/v1/plants/search?token=jtHCb1MJRZLxvgOgWd_yPQpkLDgBjChRU7RbdQw6ciI&q=bamboo"



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
         val  result= root.findViewById<SearchView>(R.id.searchbar)
        val search_bar = result.toString()
        print(search_bar)

        result.query
//        search = result.setQuery("cactus",true).toString()
        print(search_bar)
       result.clearFocus()

        fetchJson(search_bar)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_recycler.layoutManager  = LinearLayoutManager(activity)


    }

    fun fetchJson(searchin : String){
        val out = url+searchin

        val request = Request.Builder().url(url).build()


        client.newCall(request).enqueue(object: Callback{

            override fun onFailure(call: Call, e: IOException) {
                println("There was a failure getting data")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)
                val gson = GsonBuilder().create()

                val homeFeed = gson.fromJson(body, Searchdata::class.java)

                activity?.runOnUiThread {
                    search_recycler.adapter = searchAdapter(homeFeed)

                }
//                if(!response.isSuccessful){
//                    println(response)
//                    println("data printing here")
//
//                }else{
//                    println("Nothing returned " + response)
//                }
            }

        })

    }
}


