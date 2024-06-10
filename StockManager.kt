package com.example.projectcafe

object StockManager {

    private val stock = mutableMapOf(
        "Espresso" to 50,
        "Latte" to 50,
        "Macchiato" to 50,
        "Cappuccino" to 50,
        "Frappucino" to 50,
        "Plain Muffin" to 50,
        "Blueberry Muffin" to 50,
        "Chocolate Chip Muffin" to 50,
        "Brownie" to 50,
        "Cake Pop" to 50,
        "Strudel" to 50,
        "Soft Pretzel" to 50,
        "Hot Chocolate" to 50,
        "Plain Bagel" to 50,
        "Bagel with Sesame Seeds" to 50
    )

    fun getStock(itemName: String): Int {
        return stock[itemName] ?: 0 // Return the stock quantity for the given item
    }

    fun getStockInfo(): List<Pair<String, Int>> {
        return stock.toList()
    }

    fun reduceStock(itemName: String, quantity: Int) {
        stock[itemName] = (stock[itemName] ?: 0) - quantity
    }
}
