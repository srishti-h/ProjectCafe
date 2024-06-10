package com.example.projectcafe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectcafe.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView
        cartAdapter = CartAdapter(Cart.items) { position -> showDeleteConfirmationDialog(position) }
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.cartRecyclerView.adapter = cartAdapter

        // Back button functionality
        binding.backButton.setOnClickListener {
            finish()
        }

        // Checkout button functionality
        binding.checkoutButton.setOnClickListener {
            // Calculate total quantity of items in the cart
            var totalQuantityInCart = 0
            for (item in Cart.items) {
                totalQuantityInCart += item.quantity
            }

            // Check if stock is sufficient for checkout
            var isStockSufficient = true
            for (item in Cart.items) {
                val stockQuantity = StockManager.getStock(item.name)
                if (totalQuantityInCart > stockQuantity) {
                    // If total quantity in cart exceeds available stock for any item, set flag to false
                    isStockSufficient = false
                    break
                }
            }

            if (isStockSufficient) {
                // Proceed with the checkout process
                for (item in Cart.items) {
                    StockManager.reduceStock(item.name, item.quantity)
                }
                // Clear the cart after successful checkout
                Cart.clear()
                // Show checkout success message
                Toast.makeText(this, "Checkout successful!", Toast.LENGTH_SHORT).show()
                // Navigate back to the main activity
                startActivity(Intent(this, BlankActivity::class.java))
            } else {
                // Display error message if stock is insufficient
                Toast.makeText(this, "Not enough stock available for some items", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Show confirmation dialog for deletion
    private fun showDeleteConfirmationDialog(position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Do you want to remove one (1) of this item from your cart?")

        // Yes button
        builder.setPositiveButton("Yes") { dialog, _ ->
            val cartItem = Cart.items[position]
            // Remove item from cart
            Cart.removeItem(cartItem.name, 1)
            // Dynamically update the UI
            cartAdapter.notifyItemRemoved(position)
            dialog.dismiss()
        }

        // No button
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        // Create and show the dialog
        val dialog = builder.create()
        dialog.show()

        // Customize button text colors
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).apply {
            setTextColor(ContextCompat.getColor(this@CartActivity, R.color.pink))
        }
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).apply {
            setTextColor(ContextCompat.getColor(this@CartActivity, R.color.pink))
        }
    }
}
