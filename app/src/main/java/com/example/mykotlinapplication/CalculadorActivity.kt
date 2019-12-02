package com.example.mykotlinapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_calculador.*


class CalculadorActivity : AppCompatActivity() {

    var n1 = 0
    var n2 = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculador)

        n1 = intent.getIntExtra("num1", 0)
        n2 = intent.getIntExtra("num2", 0)

        a2num1.text = n1.toString()
        a2num2.text = n2.toString()

        buttonSuma.setOnClickListener { retornarResultado(it) }
        buttonResta.setOnClickListener { retornarResultado(it) }
        buttonDivision.setOnClickListener { retornarResultado(it) }
        buttonProducto.setOnClickListener { retornarResultado(it) }
        buttonModulo.setOnClickListener { retornarResultado(it) }

    }

    fun retornarResultado(it: View?) {
        val btn = it as Button
        val op = btn.text

        val result = when(op){
            "+" -> n1 + n2
            "-" -> n1 - n2
            "*" -> n1 * n2
            "/" -> n1 / n2
            "%" -> n1 % n2

            else -> 0
        }

        val intent = Intent()
        intent.putExtra("result", result)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
