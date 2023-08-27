package com.example.shoppinglist.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.data.items.ShoppingItem
import com.example.shoppinglist.databinding.DialogAddShoppingItemBinding

class AddShoppingItemDialog(context: Context, var listener: AddShoppingItemDialog.Listener): AppCompatDialog(context) {

    private lateinit var binding: DialogAddShoppingItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddShoppingItemBinding.inflate(LayoutInflater.from(context));
        setContentView(binding.root)

        binding.tvAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val amount = binding.etAmount.text.toString()

            if (name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Some fields are missing!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val shoppingItem = ShoppingItem(name, amount.toInt())
            listener.onAddShoppingItem(shoppingItem)
            dismiss()
        }

        binding.tvCancel.setOnClickListener {
            cancel()
        }
    }

    interface Listener {
        fun onAddShoppingItem(shoppingItem: ShoppingItem)
    }
}