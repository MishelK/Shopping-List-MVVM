package com.example.shoppinglist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.items.ShoppingItem
import com.example.shoppinglist.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repository: ShoppingRepository): ViewModel() {

    fun upsert(shoppingItem: ShoppingItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.upsert(shoppingItem)
    }

    fun delete(shoppingItem: ShoppingItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.delete(shoppingItem)
    }

    fun getAllShoppingItemsLiveData(): LiveData<List<ShoppingItem>> = MutableLiveData(repository.getAllShoppingItems())
}