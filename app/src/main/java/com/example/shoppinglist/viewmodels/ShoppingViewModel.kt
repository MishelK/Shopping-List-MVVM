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

    private var shoppingItemsLiveData: MutableLiveData<List<ShoppingItem>> = MutableLiveData()

    fun upsert(shoppingItem: ShoppingItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.upsert(shoppingItem)
        getAllShoppingItemsLiveData()
    }

    fun delete(shoppingItem: ShoppingItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.delete(shoppingItem)
        getAllShoppingItemsLiveData()
    }

    fun getAllShoppingItemsLiveData(): LiveData<List<ShoppingItem>> = shoppingItemsLiveData.also {
        CoroutineScope(Dispatchers.IO).launch {
            val items = repository.getAllShoppingItems()
            CoroutineScope(Dispatchers.Main).launch {
                shoppingItemsLiveData.value = items
            }
        }
    }
}