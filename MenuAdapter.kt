package com.example.projectcafe

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(private val items: List<String>, private val context: Context) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val menuItemText: TextView = itemView.findViewById(R.id.menuItemText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.menuItemText.text = items[position]

        // Set OnClickListener for each item
        holder.itemView.setOnClickListener {
            // Navigate to a different activity based on the clicked item
            val intent = when (position) {
                0 -> Intent(context, EspressoActivity::class.java)
                1 -> Intent(context, LatteActivity::class.java)
                2 -> Intent(context, MacchiatoActivity::class.java)
                3 -> Intent(context, CappuccinoActivity::class.java)
                4 -> Intent(context, FrappuccinoActivity::class.java)
                5 -> Intent(context, PlainMuffinActivity::class.java)
                6 -> Intent(context, BlueberryMuffinActivity::class.java)
                7 -> Intent(context, ChocolateChipMuffinActivity::class.java)
                8 -> Intent(context, BrownieActivity::class.java)
                9 -> Intent(context, CakePopActivity::class.java)
                10 -> Intent(context, StrudelActivity::class.java)
                11 -> Intent(context, SoftPretzelActivity::class.java)
                12 -> Intent(context, HotChocolateActivity::class.java)
                13 -> Intent(context, PlainBagelActivity::class.java)
                14 -> Intent(context, SesameSeedBagelActivity::class.java)
                else -> null
            }
            intent?.let { context.startActivity(it) }
        }
    }

    override fun getItemCount(): Int = items.size
}
