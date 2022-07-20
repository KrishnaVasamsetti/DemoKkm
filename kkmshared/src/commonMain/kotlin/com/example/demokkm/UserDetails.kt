package com.example.demokkm

class UserDetails {

    fun isValidDetails(userName: String, password: String): Boolean {
        return userName.isNotEmpty() && password.isNotEmpty()
    }
}