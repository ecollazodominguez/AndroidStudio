package com.example.mykotlinapplication

import android.Manifest
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main2.*
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.jetbrains.anko.*


class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        textView.text = intent.getStringExtra("color")
        btnBack.setOnClickListener { volver(it) }
        buttonCall.setOnClickListener { call(it) }
        buttonGoogle.setOnClickListener { google(it) }
        buttonCamera.setOnClickListener { camera(it) }
        when(textView.text){
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
                    Manifest.permission.CALL_PHONE)) {
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

    private fun camera(it: View?){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                toast("TRUE?")
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CAMERA),
                    1)
            } else {
                toast("RECHAZADO POR SIEMPRE")

            }
        } else {
            toast("ya permitido!")
            val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent,0)
        }
        }
    }

