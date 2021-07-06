package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import java.lang.Math.cbrt
import kotlin.math.*


class MainActivity : AppCompatActivity() {
    var secondNumber : Double = 0.0
    var factorial : Double = 1.0
    var lastNumber : Double = 0.0
    var currentOperation: Operation? = null
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addCallbacks()

    }
    private fun addCallbacks() {
        binding.clear.setOnClickListener {
            clearInput()
        }
        binding.plus.setOnClickListener {
            prepareOPeration(Operation.Plus)
        }
        binding.Minus.setOnClickListener {
            prepareOPeration(Operation.Minus)
        }
        binding.Div.setOnClickListener {
            prepareOPeration(Operation.Div)
        }
        binding.Multiplication.setOnClickListener {
            prepareOPeration(Operation.Multiplication)
        }
        binding.percentage.setOnClickListener {
            prepareOPeration(Operation.Percentage)
        }
        binding.sin.setOnClickListener {
            prepareOPeration(Operation.Sin)
        }
        binding.cos.setOnClickListener {
            prepareOPeration(Operation.Cos)
        }
        binding.tan.setOnClickListener {
            prepareOPeration(Operation.Tan)
        }
        binding.Log2.setOnClickListener {
            prepareOPeration(Operation.Log2)
        }
        binding.Log10.setOnClickListener {
            prepareOPeration(Operation.Log10)
        }
        binding.ln.setOnClickListener {
            prepareOPeration(Operation.Ln)
        }
        binding.exp.setOnClickListener {
            prepareOPeration(Operation.Exp)
        }
        binding.X2.setOnClickListener {
            prepareOPeration(Operation.X2)
        }

        binding.Xn.setOnClickListener {
            prepareOPeration(Operation.Xn)
        }
        binding.squareRoot.setOnClickListener {
            prepareOPeration(Operation.SquareRoot)
        }
        binding.cubeRoot.setOnClickListener {
            prepareOPeration(Operation.CubeRoot)
        }
        binding.sinh.setOnClickListener {
            prepareOPeration(Operation.Sinh)
        }
        binding.cosh.setOnClickListener {
            prepareOPeration(Operation.Cosh)
        }
        binding.tanh.setOnClickListener {
            prepareOPeration(Operation.Tanh)
        }
        binding.mod.setOnClickListener {
            prepareOPeration(Operation.Mod)
        }
        binding.factorial.setOnClickListener {
            prepareOPeration(Operation.Factorial)
        }
        binding.xx.setOnClickListener {
            prepareOPeration(Operation.XX)
        }
        binding.Result.setOnClickListener {
            val result = doCurrentOpertion()
            binding.numbers.text = result.toString()
        }
    }
    private fun doCurrentOpertion(): Double {
        return when(currentOperation){
            Operation.Plus -> {
                secondNumber()
                lastNumber + secondNumber
            }
            Operation.Minus -> {
                secondNumber()
                lastNumber - secondNumber
            }
            Operation.Div -> {
                secondNumber()
                lastNumber / secondNumber
            }
            Operation.Multiplication -> {
                secondNumber()
                lastNumber * secondNumber
            }
            Operation.Percentage -> {
                secondNumber()
                lastNumber / 100 * secondNumber
            }
            Operation.X2 -> lastNumber * lastNumber
            Operation.Xn -> {
                secondNumber()
                lastNumber.pow(secondNumber)
            }
            Operation.XX -> 1 / lastNumber
            Operation.Sin -> sin(lastNumber)
            Operation.Cos -> cos(lastNumber)
            Operation.Tan -> tan(lastNumber)
            Operation.Log2 -> log2(lastNumber)
            Operation.Log10 -> log10(lastNumber)
            Operation.Ln -> ln(lastNumber)
            Operation.Exp -> exp(lastNumber)
            Operation.Mod -> {
                secondNumber()
                lastNumber.rem(secondNumber)
            }
            Operation.Sinh -> sinh(lastNumber)
            Operation.Cosh -> cosh(lastNumber)
            Operation.Tanh -> tanh(lastNumber)
            Operation.SquareRoot -> sqrt(lastNumber)
            Operation.CubeRoot -> cbrt(lastNumber)
            Operation.Factorial -> {
                factorial()
                factorial
            }

            else -> 0.0
        }
    }
    fun factorial(){
        var number = lastNumber
        for(i in 1.. number.toInt()){
            factorial*= i
        }

    }

    private fun prepareOPeration(operation: Operation) {
        lastNumber = binding.numbers.text.toString().toDouble()
        clearInput()
        currentOperation = operation
    }

    private fun clearInput() {
        binding.numbers.text = ""
    }

    private fun secondNumber(){
        secondNumber = binding.numbers.text.toString().toDouble()
    }


    fun onClickNumber(v: View){
        val newDigit = (v as Button).text.toString()
        val oldDigit = binding.numbers.text.toString()
        val newTextNumber = oldDigit + newDigit
        binding.numbers.text = newTextNumber
    }
    fun Delete(v: View){
        var text = binding.numbers.text
        binding.numbers.text = text.substring(0, text.length-1)
    }

}

