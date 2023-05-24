package com.example.recipe_app.adapters

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recipe_app.R
import com.example.recipe_app.databinding.IngredientsRvItemBinding
import com.example.recipe_app.models.ExtendedIngredient
import com.example.recipe_app.models.ExtendedIngredientX
import com.squareup.picasso.Picasso

class IngredientsRVAdapter(private val context: Context) : ListAdapter<ExtendedIngredientX , IngredientsRVAdapter.IngredientsRVViewHolder>(DiffUtilComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsRVViewHolder {
        val view = IngredientsRvItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return IngredientsRVViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientsRVViewHolder, position: Int) {
        val currItem = getItem(position)

        holder.ingredientQty.text = Html.fromHtml("quantity: ${currItem.amount}")
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+currItem.image).placeholder(R.drawable.ic_launcher_foreground).into(holder.ingredientsImage)
        holder.ingredientsName.text = currItem.name
        holder.ingredientsName.isSelected = true

    }


    inner class IngredientsRVViewHolder(private val binding: IngredientsRvItemBinding) : ViewHolder(binding.root){
        val ingredientQty = binding.ingredientQuantity
        val ingredientsImage = binding.ingredientsImage
        val ingredientsName = binding.ingredientsName
    }

    class DiffUtilComparator : DiffUtil.ItemCallback<ExtendedIngredientX>() {
        override fun areItemsTheSame(
            oldItem: ExtendedIngredientX,
            newItem: ExtendedIngredientX
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ExtendedIngredientX,
            newItem: ExtendedIngredientX
        ): Boolean {
            return oldItem == newItem
        }

    }

}