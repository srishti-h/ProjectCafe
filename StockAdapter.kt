package com.example.projectcafe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StockAdapter(private val stockItems: List<Pair<String, Int>>) :
    RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.stock_list_item, parent, false)
        return StockViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val (itemName, quantity) = stockItems[position]
        holder.itemNameTextView.text = itemName
        holder.itemQuantityTextView.text = "$quantity in stock"
    }

    override fun getItemCount() = stockItems.size

    inner class StockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNameTextView: TextView = itemView.findViewById(R.id.itemNameTextView)
        val itemQuantityTextView: TextView = itemView.findViewById(R.id.itemQuantityTextView)
    }
}
