package com.example.abgusov_lab5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
        val paperFormatGroup = findViewById<android.widget.RadioGroup>(R.id.paperFormatGroup)

        calculateButton.setOnClickListener {
            val pageCountStr = pageCountEditText.text.toString()

            if (pageCountStr.isEmpty()) {
                Toast.makeText(this, "Введите количество страниц", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val pageCount = pageCountStr.toIntOrNull() ?: 0
            if (pageCount <= 0) {
                Toast.makeText(this, "Количество страниц должно быть больше 0", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedId = paperFormatGroup.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(this, "Выберите формат листа", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val pricePerPage = when (selectedId) {
                R.id.a4RadioButton -> a4Price
                R.id.a3RadioButton -> a3Price
                R.id.a1RadioButton -> a1Price
                else -> 0
            }

            val totalPrice = pageCount * pricePerPage

            val intent = Intent(this, OrderSummaryActivity::class.java).apply {
                putExtra("TOTAL_PRICE", totalPrice)
                println("Передаваемая сумма: $totalPrice")
            }
            startActivity(intent)
        }
    }
}