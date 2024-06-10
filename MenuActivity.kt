package com.example.projectcafe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectcafe.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val menuItems = listOf(
            "Espresso", "Latte", "Macchiato", "Cappuccino", "Frappuccino",
            "Plain Muffin", "Blueberry Muffin", "Chocolate Chip Muffin", "Brownie", "Cake Pop",
            "Strudel", "Soft Pretzel", "Hot Chocolate", "Plain Bagel", "Bagel with Sesame Seeds"
        )


        val allItems = menuItems

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = MenuAdapter(menuItems, this)

        binding.backButton.setOnClickListener {
            val intent = Intent(this, BlankActivity::class.java)
            startActivity(intent)
        }

        binding.viewCartButton.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }
}
