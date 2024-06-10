package com.example.projectcafe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val backButton = findViewById<Button>(R.id.backButton)
        val applePayButton = findViewById<Button>(R.id.applePayButton)
        val paypalButton = findViewById<Button>(R.id.paypalButton)
        val creditCardButton = findViewById<Button>(R.id.creditCardButton)

        backButton.setOnClickListener {
            finish()
        }

        val checkoutListener = {
            Toast.makeText(this, "Checked out successfully!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, BlankActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        applePayButton.setOnClickListener { checkoutListener() }
        paypalButton.setOnClickListener { checkoutListener() }
        creditCardButton.setOnClickListener { checkoutListener() }
    }
}
