package com.example.projectcafe

object UserStore {
    private val users = mutableMapOf<String, String>() // Stores username to password

    // Add a new user
    fun addUser(username: String, password: String): Boolean {
        if (users.containsKey(username)) {
            return false // Username already exists
        }
        users[username] = password
        return true
    }

    // Validate user credentials
    fun validateUser(username: String, password: String): Boolean {
        return users[username] == password
    }

    // Optional: Clear all users (useful for debugging or resetting)
    fun clearUsers() {
        users.clear()
    }

    // Optional: Get all users (useful for debugging)
    fun getAllUsers(): Map<String, String> {
        return users
    }
}
