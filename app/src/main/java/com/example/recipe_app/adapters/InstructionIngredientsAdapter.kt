package com.example.recipe_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recipe_app.R
import com.example.recipe_app.databinding.ListInstructionEquipmentsIngredientsBinding
import com.example.recipe_app.models.IngredientX
import com.squareup.picasso.Picasso

class InstructionIngredientsAdapter(private val context: Context) : ListAdapter<IngredientX , InstructionIngredientsAdapter.InstructionIngredientsAdapterViewHolder>(DiffUtilComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionIngredientsAdapterViewHolder {
        val view = ListInstructionEquipmentsIngredientsBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return InstructionIngredientsAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: InstructionIngredientsAdapterViewHolder, position: Int) {
        val currItem = getItem(position)

        holder.itemName.text = currItem.name
        holder.itemName.isSelected = true

        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/${currItem.image}").placeholder(R.drawable.ic_launcher_foreground).into(holder.itemImage)
    }

    inner class InstructionIngredientsAdapterViewHolder(private val binding: ListInstructionEquipmentsIngredientsBinding) : ViewHolder(binding.root){
        val itemImage = binding.imageInstructionStep
        val itemName = binding.nameInstructionStep
    }

    class DiffUtilComparator : DiffUtil.ItemCallback<IngredientX>(){
        override fun areItemsTheSame(oldItem: IngredientX, newItem: IngredientX): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: IngredientX, newItem: IngredientX): Boolean {
            return oldItem == newItem
        }
    }
}