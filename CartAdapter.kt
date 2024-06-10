package com.example.projectcafe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private var items: List<CartItem>,
    private val onRemoveItem: (Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.itemName)
        val itemQuantity: TextView = itemView.findViewById(R.id.itemQuantity)
        val removeButton: Button = itemView.findViewById(R.id.removeButton)

        init {
            removeButton.setOnClickListener {
                onRemoveItem(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item_layout, parent, false)
        return CartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = items[position]
        holder.itemName.text = cartItem.name
        holder.itemQuantity.text = cartItem.quantity.toString()
    }

    override fun getItemCount() = items.size

    fun removeItem(position: Int) {
        items = items.toMutableList().apply { removeAt(position) }
        notifyItemRemoved(position)
    }
}
