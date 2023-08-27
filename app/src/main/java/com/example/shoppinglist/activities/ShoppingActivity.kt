package com.example.shoppinglist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.adapters.ShoppingItemAdapter
import com.example.shoppinglist.data.items.ShoppingItem
import com.example.shoppinglist.databinding.ActivityShoppingBinding
import com.example.shoppinglist.dialogs.AddShoppingItemDialog
import com.example.shoppinglist.viewmodels.ShoppingViewModel
import com.example.shoppinglist.viewmodels.ShoppingViewModelFactory
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val shoppingViewModelFactory: ShoppingViewModelFactory by instance()

    private lateinit var binding: ActivityShoppingBinding
    private lateinit var shoppingViewModel: ShoppingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        shoppingViewModel = ViewModelProvider(this, shoppingViewModelFactory)[ShoppingViewModel::class.java]

        val shoppingItemAdapter = ShoppingItemAdapter(listOf(), shoppingViewModel)
        binding.rvShoppingItems.layoutManager = LinearLayoutManager(this)
        binding.rvShoppingItems.adapter = shoppingItemAdapter

        shoppingViewModel.getAllShoppingItemsLiveData().observe(this) {
            shoppingItemAdapter.items = it
            shoppingItemAdapter.notifyDataSetChanged()
        }

        binding.fab.setOnClickListener {
            AddShoppingItemDialog(this, object: AddShoppingItemDialog.Listener {
                override fun onAddShoppingItem(shoppingItem: ShoppingItem) {
                    shoppingViewModel.upsert(shoppingItem)
                }
            }).show()
        }
    }
}