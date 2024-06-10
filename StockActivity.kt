package com.example.projectcafe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StockActivity : AppCompatActivity() {
    private lateinit var stockRecyclerView: RecyclerView
    private lateinit var stockAdapter: StockAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock)

        stockRecyclerView = findViewById(R.id.stockRecyclerView)
        val backButton = findViewById<Button>(R.id.backButton)

        val stockItems = StockManager.getStockInfo()
        stockAdapter = StockAdapter(stockItems)

        stockRecyclerView.layoutManager = LinearLayoutManager(this)
        stockRecyclerView.adapter = stockAdapter

        backButton.setOnClickListener {
            startActivity(Intent(this, BlankActivity::class.java))
        }
    }
}
