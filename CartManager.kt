package com.example.projectcafe

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CartManager {
    private const val PREF_NAME = "CartPrefs"
    private const val KEY_USER = "user"
    private const val KEY_CART = "cart"

    private lateinit var sharedPreferences: SharedPreferences
    private var currentUser: User? = null
    private var cartItems: MutableList<CartItem> = mutableListOf()

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        loadUserData()
        loadCartData()
    }

    fun setUser(user: User) {
        currentUser = user
        saveUserData()
    }

    fun addItemToCart(item: CartItem) {
        // Check if item already exists in the cart, update quantity if so
        val existingItem = cartItems.find { it.name == item.name }
        if (existingItem != null) {
            existingItem.quantity += item.quantity
        } else {
            cartItems.add(item)
        }
        saveCartData()
    }

    fun clearCart() {
        cartItems.clear()
        saveCartData()
    }

    fun getCartItems(): MutableList<CartItem> {
        return cartItems.toMutableList() // Return a copy of the mutable list
    }

    private fun saveUserData() {
        val gson = Gson()
        val userJson = gson.toJson(currentUser)
        sharedPreferences.edit().putString(KEY_USER, userJson).apply()
    }

    private fun saveCartData() {
        val gson = Gson()
        val cartJson = gson.toJson(cartItems)
        sharedPreferences.edit().putString(KEY_CART, cartJson).apply()
    }

    private fun loadUserData() {
        val gson = Gson()
        val userJson = sharedPreferences.getString(KEY_USER, null)
        currentUser = gson.fromJson(userJson, User::class.java)
    }

    private fun loadCartData() {
        val gson = Gson()
        val cartJson = sharedPreferences.getString(KEY_CART, null)
        cartItems = gson.fromJson(cartJson, object : TypeToken<MutableList<CartItem>>() {}.type)
            ?: mutableListOf()
    }

    fun clearData() {
        sharedPreferences.edit().clear().apply()
    }
}
