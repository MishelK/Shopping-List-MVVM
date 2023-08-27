package com.example.shoppinglist.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.shoppinglist.R
import com.example.shoppinglist.data.repositories.ShoppingRepository
import com.example.shoppinglist.data.repositories.db.ShoppingDatabase
import com.example.shoppinglist.ui.viewmodels.ShoppingViewModel
import com.example.shoppinglist.ui.viewmodels.ShoppingViewModelFactory

class ShoppingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val repository = ShoppingRepository(ShoppingDatabase(this))
        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[ShoppingViewModel::class.java]
    }
}