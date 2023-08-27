package com.example.shoppinglist.data.repositories.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoppinglist.data.items.ShoppingItem

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(shoppingItem: ShoppingItem)

    @Delete
    suspend fun delete(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems(): List<ShoppingItem>
}