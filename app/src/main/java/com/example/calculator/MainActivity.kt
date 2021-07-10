package com.example.calculator

import android.os.Bundle
import android.util.Log
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
    }
    private fun Result(operation : String) {
        val result = Expression(operation).calculate()
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
                Result(t.toString())
            },
            { e ->
                Log.i("TAG", "ON error")
            }
        )
    }
}

