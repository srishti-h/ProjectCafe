import com.example.projectcafe.CartItem

object Cart {
    val items = mutableListOf<CartItem>()

    fun addItem(item: CartItem) {
        items.add(item)
    }
    fun clear() {
        items.clear()
    }

    fun removeItem(name: String, quantity: Int) {
        val existingItem = items.find { it.name == name }
        if (existingItem != null) {
            existingItem.quantity -= quantity
            if (existingItem.quantity <= 0) {
                items.remove(existingItem)
            }
        }
    }
}
