package com.example.shoppinglist.data.repositories

import com.example.shoppinglist.data.items.ShoppingItem
import com.example.shoppinglist.data.repositories.db.ShoppingDatabase

class ShoppingRepository(private val database: ShoppingDatabase) {

    suspend fun upsert(shoppingItem: ShoppingItem) = database.getShoppingDao().upsert(shoppingItem)

    suspend fun delete(shoppingItem: ShoppingItem) = database.getShoppingDao().delete(shoppingItem)

    fun delete() = database.getShoppingDao().getAllShoppingItems()
}