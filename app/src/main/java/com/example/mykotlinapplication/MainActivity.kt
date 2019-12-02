package com.example.mykotlinapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import java.time.Duration

const val CALC_REQUEST = 1  // The request code


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAzul.setOnClickListener{abrirActivity2(it)}
        btnVerde.setOnClickListener{abrirActivity2(it)}
        btnCalc.setOnClickListener {  abrirActivityCalc(it)}

    }


    fun abrirActivity2(v: View){
        val btn = v as Button
        startActivity(intentFor<Main2Activity>("color" to btn.text).singleTop())

    }
    fun abrirActivityCalc(v: View){
        var num1 = a1num1.text
        var num2 = a1num2.text

        try {

            var n1 = Integer.parseInt(num1.toString())
            var n2 = Integer.parseInt(num2.toString())

            val intent = Intent(this, CalculadorActivity::class.java)
            intent.putExtra("num1", n1)
            intent.putExtra("num2", n2)

            startActivityForResult(intent, CALC_REQUEST)



        }catch (nfe: NumberFormatException){
            Toast.makeText(this, "Numero Invalido", Toast.LENGTH_SHORT)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Check which request we're responding to
        if (requestCode == CALC_REQUEST) {
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {

                val result =data?.getIntExtra("result", 0)
                resultado.text = result.toString()

            }
        }
    }
}
