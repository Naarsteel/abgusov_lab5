package com.example.abgusov_lab5

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OrderSummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)

        val totalPrice = intent.getIntExtra("TOTAL_PRICE", 0)

        val orderSummaryTextView = findViewById<TextView>(R.id.orderSummaryTextView)

        orderSummaryTextView.text = "Сумма заказа = $totalPrice руб."

        findViewById<Button>(R.id.closeButton).setOnClickListener {
            finish()
        }
    }
}