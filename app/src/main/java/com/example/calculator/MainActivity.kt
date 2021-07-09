package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.calculator.databinding.ActivityMainBinding
import io.reactivex.rxjava3.core.Observable
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        RX()
        addCallbacks()
    }
    private fun addCallbacks() {
        binding.appCompatButton18.setOnClickListener {
            binding.numbers.text = addToInputText(".")
        }
        binding.Div.setOnClickListener {
            binding.numbers.text = addToInputText("÷")
        }
        binding.Multiplication.setOnClickListener {
            binding.numbers.text = addToInputText("×")
        }
        binding.Minus.setOnClickListener {
            binding.numbers.text = addToInputText("-")
        }
        binding.plus.setOnClickListener {
            binding.numbers.text = addToInputText("+")
        }
        binding.clear.setOnClickListener {
            clearInput()
        }
    }

    private fun clearInput() {
        binding.numbers.text = ""
    }

    fun onClickNumber(v: View){
        val newDigit = (v as Button).text.toString()
        val oldDigit = binding.numbers.text.toString()
        val newTextNumber = oldDigit + newDigit
        binding.numbers.text = newTextNumber
    }

    private fun addToInputText(buttonValue: String): String {
        return "${binding.numbers.text}$buttonValue"
    }

    private fun getInputExpression(): String {
        var expression = binding.numbers.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }

    private fun showResult() {
        val expression = getInputExpression()
        val result = Expression(expression).calculate()
        if (result.isNaN()) {
            binding.operation.text = "Error"
        } else {
            binding.operation.text = DecimalFormat("0.######").format(result).toString()
        }
    }

    fun RX(){
        val observable = Observable.create<String>{ emitter ->
            binding.numbers.doOnTextChanged { text, start, before, count ->
                emitter.onNext(text.toString())
            }
        }.debounce(1, TimeUnit.SECONDS)
        observable.subscribe(
            { t ->
                showResult()
            },
            { e ->
                Log.i("TAG", "ON error")
            }
        )
    }
}

