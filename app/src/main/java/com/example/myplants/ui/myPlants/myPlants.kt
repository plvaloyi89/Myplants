package com.example.myplants.ui.myPlants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myplants.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.firebase.ui.database.SnapshotParser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class myPlants : Fragment() {
    

    lateinit var  plantrecycler : RecyclerView
    lateinit var mbase : DatabaseReference
    lateinit var connect : FirebaseRecyclerAdapter<plants,MyPlantsViewModel>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.my_plants_fragment, container, false)



        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        
        plantrecycler = view.findViewById(R.id.plants_list)
        mbase = FirebaseDatabase.getInstance().getReference("myplants")

        val options: FirebaseRecyclerOptions<plants> = FirebaseRecyclerOptions.Builder<plants>()
            .setQuery(mbase, object : SnapshotParser<plants?> {
                override fun parseSnapshot(snapshot: DataSnapshot): plants {
                    return plants(
                        snapshot.child("common_name").value.toString(),
                        snapshot.child("image_url").value.toString()
                    )
                }
            })
            .build()




       connect = object : FirebaseRecyclerAdapter<plants,MyPlantsViewModel>(options){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPlantsViewModel {
                return  MyPlantsViewModel(LayoutInflater.from(parent.context).inflate(R.layout.plantl_list,parent,false))
            }

            override fun onBindViewHolder(holder: MyPlantsViewModel, position: Int, model: plants) {
                holder.bind(model)
            }


        }

        val layoutManager = LinearLayoutManager(context)
        layoutManager.reverseLayout = false
        plantrecycler.setHasFixedSize(true)
        plantrecycler.layoutManager = layoutManager
        plantrecycler.setAdapter(connect)

        }


    override fun onStart() {
        super.onStart()
        connect.startListening()
    }

    override fun onStop() {
        super.onStop()
        connect.stopListening()
    }







    companion object {
        fun newInstance() = myPlants()
    }

}

