package com.example.recipe_app.adapters

import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe_app.R
import com.example.recipe_app.RecipeDetailsActivity
import com.example.recipe_app.databinding.RandomRecipeRvBinding
import com.example.recipe_app.models.Recipe
import com.squareup.picasso.Picasso

class RandomRecipeAdapter(private val context: Context) : androidx.recyclerview.widget.ListAdapter<Recipe , RandomRecipeAdapter.RandomRecipeViewHolder>(DiffUtilComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomRecipeViewHolder {
        val view = RandomRecipeRvBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return RandomRecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RandomRecipeViewHolder, position: Int) {
        val currItem = getItem(position)
        holder.recipeTitle.text = currItem.title
        holder.recipeTitle.isSelected = true

        Picasso.get().load(currItem.image).placeholder(R.drawable.ic_launcher_foreground).into(holder.recipeImage)

        holder.recipeServings.text = Html.fromHtml("${currItem.servings} servings")
        holder.recipeLikes.text =  Html.fromHtml("${currItem.aggregateLikes} likes")
        holder.recipeTime.text = Html.fromHtml("${currItem.readyInMinutes} minutes")


        holder.cardViewItem.setOnClickListener(View.OnClickListener {
            val intent = Intent(context , RecipeDetailsActivity::class.java)
            // we might have to add put extra
            intent.putExtra("recipeID" , currItem.id.toString())
            context.startActivity(intent)
        })

    }


    inner class RandomRecipeViewHolder(private val binding: RandomRecipeRvBinding) : RecyclerView.ViewHolder(binding.root){
        val recipeImage = binding.imageViewFood
        val recipeTitle = binding.textViewTitle
        val recipeLikes = binding.textLikes
        val recipeTime = binding.textTime
        val recipeServings = binding.textServings
        val cardViewItem = binding.cardViewItem
    }

    class DiffUtilComparator : DiffUtil.ItemCallback<Recipe>(){
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }

    }

}