package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EdgeEffect
import android.widget.EditText
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    //textview used to display the input and output
    lateinit var etShowNumber: TextView

    var lastNumeric: Boolean= false

    var stateError: Boolean= false

    var lastDot: Boolean= false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etShowNumber= findViewById(R.id.resultTextview)
    }
    fun onDigit(view: View){
        if (stateError){
            etShowNumber.text= (view as Button).text
            stateError= false
        } else{
            etShowNumber.append((view as Button).text)
        }
        lastNumeric= true
    }
    fun onDecimalPoint(view: View){
        if (lastNumeric&& !stateError &&!lastNumeric){
            etShowNumber.append(".")
            lastNumeric=false
            lastDot=true
        }
    }
    fun onOperator(view: View){
        if (lastNumeric && !stateError){
            etShowNumber.append((view as Button).text)
            lastNumeric= false
            lastDot= false
        }
    }
    fun onClear(view: View){
        this.etShowNumber.text=""
        lastNumeric= false
        stateError=false
        lastDot=false
    }
    fun onEqual(view: View){
        if (lastNumeric&& !stateError){
            val txt = etShowNumber.text.toString()
            val expression = ExpressionBuilder(txt).build()
            try {
                val result= expression.evaluate()
                etShowNumber.text= result.toString()
                lastDot= true
            } catch (ex: ArithmeticException){
                etShowNumber.text= "error"
                stateError= true
                lastNumeric=false

            }
        }
    }
}


