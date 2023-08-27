package com.example.shoppinglist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.adapters.ShoppingItemAdapter
import com.example.shoppinglist.data.items.ShoppingItem
import com.example.shoppinglist.data.repositories.ShoppingRepository
import com.example.shoppinglist.data.repositories.db.ShoppingDatabase
import com.example.shoppinglist.databinding.ActivityShoppingBinding
import com.example.shoppinglist.dialogs.AddShoppingItemDialog
import com.example.shoppinglist.viewmodels.ShoppingViewModel
import com.example.shoppinglist.viewmodels.ShoppingViewModelFactory

class ShoppingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = ShoppingRepository(ShoppingDatabase(this))
        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[ShoppingViewModel::class.java]

        val shoppingItemAdapter = ShoppingItemAdapter(listOf(), viewModel)
        binding.rvShoppingItems.layoutManager = LinearLayoutManager(this)
        binding.rvShoppingItems.adapter = shoppingItemAdapter

        viewModel.getAllShoppingItemsLiveData().observe(this) {
            shoppingItemAdapter.items = it
            shoppingItemAdapter.notifyDataSetChanged()
        }

        binding.fab.setOnClickListener {
            AddShoppingItemDialog(this, object: AddShoppingItemDialog.Listener {
                override fun onAddShoppingItem(shoppingItem: ShoppingItem) {
                    viewModel.upsert(shoppingItem)
                }
            }).show()
        }
    }
}