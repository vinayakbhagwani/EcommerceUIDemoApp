package com.vinayak.ecommerceuicloneapp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class CartViewModel private constructor() : ViewModel() {
    private val _cartItems = mutableStateListOf<Product>()
    val cartItems: List<Product> = _cartItems

    fun addToCart(product: Product) {
        if (!_cartItems.contains(product)) _cartItems.add(product)
    }

    fun isInCart(product: Product): Boolean = _cartItems.contains(product)

    fun getCartCount(): Int = _cartItems.size

    fun removeFromCart(product: Product): Boolean = _cartItems.remove(product)

    companion object {
        var INSTANCE: CartViewModel? = null

        fun getInstance() : CartViewModel {
            INSTANCE = if(INSTANCE==null) {
                CartViewModel()
            } else {
                INSTANCE
            }
            return INSTANCE ?: CartViewModel()
        }
    }

}