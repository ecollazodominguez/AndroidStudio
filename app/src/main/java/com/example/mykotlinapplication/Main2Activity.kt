package com.example.mykotlinapplication

import android.Manifest
import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main2.*
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import org.jetbrains.anko.*

import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*


const val CAMERA_REQUEST = 2  // The request code

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        textView.text = intent.getStringExtra("color")
        btnBack.setOnClickListener { volver(it) }
        buttonCall.setOnClickListener { call(it) }
        buttonGoogle.setOnClickListener { google(it) }
        buttonCamera.setOnClickListener { camera(it) }
        when (textView.text) {
            "Azul" -> layout.setBackgroundColor(Color.BLUE)
            "Verde" -> layout.setBackgroundColor(Color.GREEN)
        }
    }

    private fun google(it: View?) {
        browse("http://google.es")
    }

    private fun call(it: View?) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.CALL_PHONE
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                toast("TRUE?")
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    2
                )
            } else {
                toast("RECHAZADO POR SIEMPRE")

            }
        } else {
            makeCall("112")
        }
    }

    private fun volver(it: View?) {
        startActivity(intentFor<MainActivity>().singleTop())
    }

    private fun camera(it: View?) {

        //Chequea si tiene permisos
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {

            //Si no tiene y no los has rechazado por siempre, pasará por TRUE, incluso aunque denieges 1 vez
            // si rechazaste por siempre pasa por FALSE y no puede pedir permisos NUNCA.
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.CAMERA
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                toast("TRUE")
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA),
                    1
                )
            } else {
                toast("RECHAZADO POR SIEMPRE")

            }
        } else {
            toast("ya permitido!")
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST)
            cameraIntent.putExtra("foto",cameraIntent)
            setResult(Activity.RESULT_OK, cameraIntent)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Check which request we're responding to
        if (requestCode == CAMERA_REQUEST) {
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {

                val imageBitmap = data?.extras?.get("data") as Bitmap
                foto.setImageBitmap(imageBitmap)
            }
        }
    }
}





