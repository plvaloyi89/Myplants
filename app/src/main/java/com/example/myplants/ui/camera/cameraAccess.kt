package com.example.myplants.ui.camera

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Camera
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.myplants.R


class cameraAccess : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =  inflater.inflate(R.layout.camera_access_fragment, container, false)
        val start= root.findViewById<ImageView>(R.id.camera_View)
        val startButton = root.findViewById<Button>(R.id.start_Camera)
        val REQUEST_IMAGE_CAPTURE = 1

        println("Camera is present")
        val cameraIntent =  Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {

            startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE)

        }catch (e:ActivityNotFoundException){
            
        }




        return root
    }

}