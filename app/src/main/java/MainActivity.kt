package com.example.calculator


import android.content.Context
import android.content.Intent
import android.graphics.Color.red
import android.os.Build
import java.math.RoundingMode
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.calculator.R
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var main_activity_settings:ImageView = findViewById(R.id.main_activity_settings)
        main_activity_settings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent);
        }

        val math_operation: TextView = findViewById(R.id.math_operation)
        val result_text: TextView = findViewById(R.id.result_text)

        val clear_btn: TextView = findViewById(R.id.clear_btn)
        clear_btn.setOnClickListener {
            govibrate()
            math_operation.text = ""
            result_text.text = ""
        }

        val bracket_open_btn: TextView = findViewById(R.id.bracket_open_btn)
        bracket_open_btn.setOnClickListener {
            govibrate()
            math_operation.text = addToInputText("(")
        }

        val bracket_close_btn: TextView = findViewById(R.id.bracket_close_btn)
        bracket_close_btn.setOnClickListener {
            govibrate()
            math_operation.text = addToInputText(")")
        }

        val btn_0: TextView = findViewById(R.id.btn_0)
        btn_0.setOnClickListener {
            govibrate()
            math_operation.text = addToInputText("0")
        }
        val btn_1: TextView = findViewById(R.id.btn_1)
        btn_1.setOnClickListener {
            govibrate()
            math_operation.text = addToInputText("1")
        }
        val btn_2: TextView = findViewById(R.id.btn_2)
        btn_2.setOnClickListener {
            govibrate()
            math_operation.text = addToInputText("2")
        }
        val btn_3: TextView = findViewById(R.id.btn_3)
        btn_3.setOnClickListener {
            govibrate()
            math_operation.text = addToInputText("3")
        }
        val btn_4: TextView = findViewById(R.id.btn_4)
        btn_4.setOnClickListener {
            govibrate()
            math_operation.text = addToInputText("4")
        }
        val btn_5: TextView = findViewById(R.id.btn_5)
        btn_5.setOnClickListener {
            govibrate()
            math_operation.text = addToInputText("5")
        }
        val btn_6: TextView = findViewById(R.id.btn_6)
        btn_6.setOnClickListener {
            govibrate()
            math_operation.text = addToInputText("6")
        }
        val btn_7: TextView = findViewById(R.id.btn_7)
        btn_7.setOnClickListener {
            govibrate()
            math_operation.text = addToInputText("7")
        }
        val btn_8: TextView = findViewById(R.id.btn_8)
        btn_8.setOnClickListener {
            govibrate()
            math_operation.text = addToInputText("8")
        }
        val btn_9: TextView = findViewById(R.id.btn_9)
        btn_9.setOnClickListener {
            govibrate()
            math_operation.text = addToInputText("9")
        }

        val dot_btn: TextView = findViewById(R.id.dot_btn)
        dot_btn.setOnClickListener {
            govibrate()
            math_operation.text = addToInputText(".")
        }
        val division_btn: TextView = findViewById(R.id.division_btn)
        division_btn.setOnClickListener {
            govibrate()
            math_operation.text = addToInputText("/")
        }
        val mult_btn: TextView = findViewById(R.id.mult_btn)
        mult_btn.setOnClickListener {
            govibrate()
            math_operation.text = addToInputText("*")
        }
        val minus_btn: TextView = findViewById(R.id.minus_btn)
        minus_btn.setOnClickListener {
            govibrate()
            math_operation.text = addToInputText("-")
        }
        val plus_btn: TextView = findViewById(R.id.plus_btn)
        plus_btn.setOnClickListener {
            govibrate()
            math_operation.text = addToInputText("+")
        }
        val degree_btn: TextView = findViewById(R.id.degree_btn)
        degree_btn.setOnClickListener {
            govibrate()
            math_operation.text = addToInputText("^")
        }
        val sqrt_btn: TextView = findViewById(R.id.sqrt_btn)
        sqrt_btn.setOnClickListener {
            govibrate()
            math_operation.text = addToInputText("√")
        }

        val equal_btn: TextView = findViewById(R.id.equal_btn)
        equal_btn.setOnClickListener {
            govibrate()
            showResult()
        }

        val back_btn:TextView = findViewById(R.id.back_btn)
        back_btn.setOnClickListener{
            govibrate()
            val str = math_operation.text.toString()
            if (str.isNotEmpty()) {
                math_operation.text = str.substring(0,str.length-1)
            }
            result_text.text =""
        }
    }
    private fun govibrate() {
        var vibr:Long = SettingsActivity.vibr
        val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibr.toString() == "0") {
            vibratorService.cancel()
        } else {
            vibratorService.vibrate(vibr)
        }
    }

    private fun addToInputText(buttonValue: String): String {
        val math_operation: TextView = findViewById(R.id.math_operation)
        return "${math_operation.text}$buttonValue"
    }

    private fun getInputExpression(): String {
        val math_operation: TextView = findViewById(R.id.math_operation)
        var expression = math_operation.text.replace(Regex("√"), "sqrt(")
        if (expression.contains("sqrt"))
            return "$expression)"
        else
            return expression
    }

    private fun showResult() {
        val result_text: TextView = findViewById(R.id.result_text)
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()) {
                // Show Error Message
                result_text.text = "Error"
                //result_text.setTextColor(ContextCompat.getColor(this, R.color.red))
            } else {
                // Show Result
                val okr:String = SettingsActivity.asap
                val df = DecimalFormat("#.$okr")
                df.roundingMode = RoundingMode.HALF_UP
                result_text.text = df.format(result).toString()
                //result_text.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
        } catch (e: Exception) {
            // Show Error Message
            result_text.text = "Error"
            //result_text.setTextColor(ContextCompat.getColor(this, android.graphics.Color.red(1)))
        }
    }
}