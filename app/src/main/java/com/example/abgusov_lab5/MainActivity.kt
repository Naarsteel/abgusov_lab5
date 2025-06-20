package com.example.abgusov_lab5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val a4Price = 10
    private val a3Price = 30
    private val a1Price = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val pageCountEditText = findViewById<EditText>(R.id.pageCountEditText)
        val a4RadioButton = findViewById<RadioButton>(R.id.a4RadioButton)
        val a3RadioButton = findViewById<RadioButton>(R.id.a3RadioButton)
        val a1RadioButton = findViewById<RadioButton>(R.id.a1RadioButton)

        calculateButton.setOnClickListener {
            val pageCountStr = pageCountEditText.text.toString()
            if (pageCountStr.isEmpty()) {
                Toast.makeText(this, "Введите количество страниц", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val pageCount = pageCountStr.toInt()
            if (pageCount <= 0) {
                Toast.makeText(this, "Количество страниц должно быть больше 0", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedFormat = when {
                a4RadioButton.isChecked -> "A4"
                a3RadioButton.isChecked -> "A3"
                a1RadioButton.isChecked -> "A1"
                else -> {
                    Toast.makeText(this, "Выберите формат листа", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            val pricePerPage = when (selectedFormat) {
                "A4" -> a4Price
                "A3" -> a3Price
                "A1" -> a1Price
                else -> 0
            }

            val totalPrice = pageCount * pricePerPage

            val intent = Intent(this, OrderSummaryActivity::class.java)
            intent.putExtra("TOTAL_PRICE", totalPrice)
            startActivity(intent)
        }
    }
}