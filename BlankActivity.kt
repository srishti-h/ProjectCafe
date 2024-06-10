package com.example.projectcafe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BlankActivity : AppCompatActivity() {
    private lateinit var welcomeMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blank)

        val username = intent.getStringExtra("username")
        welcomeMessage = findViewById(R.id.welcomeMessage)
        val logoutButton = findViewById<Button>(R.id.logoutButton)
        val viewMenuButton = findViewById<Button>(R.id.menuButton)
        val viewCartButton = findViewById<Button>(R.id.viewCartButton)
        val viewStockButton = findViewById<Button>(R.id.viewStockButton)

        if (username != null && username.isNotEmpty()) {
            welcomeMessage.text = "Welcome, $username!"
        } else {
            welcomeMessage.text = "Welcome, Guest!"
        }

        viewMenuButton.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }

        viewCartButton.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        viewStockButton.setOnClickListener {
            startActivity(Intent(this, StockActivity::class.java))
        }

        logoutButton.setOnClickListener {
            if (username == null || username.isEmpty()) {
                // Guest user
                Cart.clear()
            }
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
