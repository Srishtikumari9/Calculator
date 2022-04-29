package com.example.calculator

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.ScriptException

class MainActivity : AppCompatActivity() {


    private lateinit var tvExpression: TextView
    private lateinit var tvResult: TextView
    private lateinit var tvClear: TextView
    private lateinit var tvDivide: TextView
    private lateinit var tvSeven: TextView
    private lateinit var tvEight: TextView
    private lateinit var tvNine: TextView
    private lateinit var tvMultiply: TextView
    private lateinit var tvFour: TextView
    private lateinit var tvFive: TextView
    private lateinit var tvSix: TextView
    private lateinit var tvMinus: TextView
    private lateinit var tvOne: TextView
    private lateinit var tvTwo: TextView
    private lateinit var tvThree: TextView
    private lateinit var tvPlus: TextView
    private lateinit var tvDot: TextView
    private lateinit var tvZero: TextView
    private lateinit var tvEquals: TextView
    private lateinit var tvBack: TextView
    private lateinit var tvPercentage: TextView
    private lateinit var tvZeroZero: TextView
    private var check = 0
    private var text: String = ""
    private lateinit var backSpace: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvExpression = findViewById(R.id.tvExpression)
        tvResult = findViewById(R.id.tvResult)
        tvClear = findViewById(R.id.tvClear)
        tvPercentage = findViewById(R.id.tvPercentage)
        tvBack = findViewById(R.id.tvBack)
        tvDivide = findViewById(R.id.tvDivide)
        tvSeven = findViewById(R.id.tvSeven)
        tvEight = findViewById(R.id.tvEight)
        tvNine = findViewById(R.id.tvNine)
        tvMultiply = findViewById(R.id.tvMultiply)
        tvFour = findViewById(R.id.tvFour)
        tvFive = findViewById(R.id.tvFive)
        tvSix = findViewById(R.id.tvSix)
        tvMinus = findViewById(R.id.tvMinus)
        tvOne = findViewById(R.id.tvOne)
        tvTwo = findViewById(R.id.tvTwo)
        tvThree = findViewById(R.id.tvThree)
        tvPlus = findViewById(R.id.tvPlus)
        tvDot = findViewById(R.id.tvDot)
        tvZero = findViewById(R.id.tvZero)
        tvEquals = findViewById(R.id.equals)
        tvBack = findViewById(R.id.tvBack)
        tvZeroZero=findViewById(R.id.zero_zero)
        tvExpression.movementMethod = ScrollingMovementMethod()
        tvExpression.isActivated = true
        tvExpression.isPressed = true


        //handling of numbers
        tvOne.setOnClickListener {
            numberHandling(it as TextView)
        }

        tvTwo.setOnClickListener {
            numberHandling(it as TextView)
        }
        tvThree.setOnClickListener {
            numberHandling(it as TextView)
        }
        tvFour.setOnClickListener {
            numberHandling(it as TextView)
        }
        tvFive.setOnClickListener {
            numberHandling(it as TextView)
        }
        tvSix.setOnClickListener {
            numberHandling(it as TextView)
        }
        tvSeven.setOnClickListener {
            numberHandling(it as TextView)
        }
        tvEight.setOnClickListener {
            numberHandling(it as TextView)
        }
        tvNine.setOnClickListener {
            numberHandling(it as TextView)
        }
        tvZero.setOnClickListener {
            numberHandling(it as TextView)
        }
        tvZeroZero.setOnClickListener{
            numberHandling(it as TextView)
        }

        //handling of operators
        tvPlus.setOnClickListener {
            operatorHandling(it as TextView)
        }
        tvMinus.setOnClickListener {
            operatorHandling(it as TextView)
        }
        tvMultiply.setOnClickListener {
            operatorHandling(it as TextView)
        }
        tvDivide.setOnClickListener {
            operatorHandling(it as TextView)
        }
        tvDot.setOnClickListener {
            operatorHandling(it as TextView)
        }
        tvPercentage.setOnClickListener{
            percentage(it as TextView)
        }

        //handling of special characters
        tvEquals.setOnClickListener {
            characterHandling()
        }
        tvClear.setOnClickListener {
            clear()
        }

        //calculation
        tvBack.setOnClickListener {
            calculation()
        }
    }

    private fun result(text: String) {
        val engine: ScriptEngine = ScriptEngineManager().getEngineByName("rhino")
        try {
            val result: Any = engine.eval(text)
            val mainResult = result.toString()
            Log.d(TAG, "not $check")

            if (check == 0) {
                tvResult.text = null
            } else {
                tvResult.text = mainResult
            }
        } catch (e: ScriptException) {
            Log.d(TAG, "ERROR")
        }
    }

    private fun operatorHandling(textView: TextView) {
        text = tvExpression.text.toString() + textView.text
        tvExpression.text = text
        check += 1
    }

    private fun numberHandling(textView: TextView) {
        text = tvExpression.text.toString() + textView.text
        tvExpression.text = text
        result(text)
    }

    private fun characterHandling() {
        text = tvResult.text.toString()
        tvExpression.text = text
        tvResult.text=null
    }

    private fun calculation() {
        if (tvExpression.text.isNotEmpty()) {
            val stringBuilder: StringBuilder = StringBuilder(tvExpression.text)
            val find = tvExpression.text.toString()
            val find2 = find.last()

            if (find2 == '+' || find2 == '-' || find2 == '*' || find2 == '/'|| find2 =='%') {
                check -= 1
            }

            stringBuilder.deleteCharAt(tvExpression.text.length - 1)
            backSpace = stringBuilder.toString()
            tvExpression.text = backSpace
            result(text = backSpace)
        }
    }

    private fun percentage(textView: TextView){
        text = tvExpression.text.toString() + textView.text
        tvExpression.text = text
        check +=1

    }

    private fun clear() {
        tvExpression.text = null
        tvResult.text = null

    }
    companion object{
        const val TAG = "calculator"
    }

}



