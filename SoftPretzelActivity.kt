package com.example.projectcafe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SoftPretzelActivity : AppCompatActivity() {
    private lateinit var quantityInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soft_pretzel)

        val backButton = findViewById<Button>(R.id.backButton)
        val addToCartButton = findViewById<Button>(R.id.addToCartButton)
        val viewCartButton = findViewById<Button>(R.id.viewCartButton)
        quantityInput = findViewById(R.id.quantityInput)

        backButton.setOnClickListener {
            finish()
        }

        addToCartButton.setOnClickListener {
            val itemName = "Soft Pretzel" // You can get the item name dynamically
            val quantity = quantityInput.text.toString().toIntOrNull() ?: 0
            val stockQuantity = StockManager.getStock(itemName)

            if (quantity > 0 && quantity <= stockQuantity) {
                val espressoItem = CartItem(itemName, quantity)
                Cart.addItem(espressoItem)
                Toast.makeText(this, "$quantity $itemName(s) added to cart", Toast.LENGTH_SHORT).show()
            } else if (quantity <= 0) {
                Toast.makeText(this, "Please enter a valid quantity", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Not enough stock available", Toast.LENGTH_SHORT).show()
            }
        }




        viewCartButton.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }
}
