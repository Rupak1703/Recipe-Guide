package com.example.recipe_app.adapters

import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recipe_app.R
import com.example.recipe_app.RecipeDetailsActivity
import com.example.recipe_app.databinding.SimilarRecipeItemBinding
import com.example.recipe_app.models.SimilarRecipeItemItem
import com.squareup.picasso.Picasso

class SimilarRecipeAdapter(private val context: Context) : ListAdapter<SimilarRecipeItemItem , SimilarRecipeAdapter.SimilarRecipeViewHolder>(DiffUtilComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarRecipeViewHolder {
        val view = SimilarRecipeItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return SimilarRecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimilarRecipeViewHolder, position: Int) {
        val currItem = getItem(position)

        holder.similarRecipeTitle.text = currItem.title
        holder.similarRecipeTitle.isSelected = true

        Picasso.get().load("https://spoonacular.com/recipeImages/${currItem.id}-556x370.${currItem.imageType}").placeholder(R.drawable.ic_launcher_foreground).into(holder.recipeImage)

        holder.recipeReadyIn.text = Html.fromHtml("<b>ready in:</b> ${currItem.readyInMinutes} min")
//        holder.recipeReadyIn.isSelected = true

        holder.cardView.setOnClickListener(View.OnClickListener {
//            Toast.makeText(context, "This Card Selected...", Toast.LENGTH_SHORT).show()
            val intent = Intent(context , RecipeDetailsActivity::class.java)
            intent.putExtra("recipeID" , currItem.id.toString())
            context.startActivity(intent)
        })

    }


    inner class SimilarRecipeViewHolder(private val binding: SimilarRecipeItemBinding) : ViewHolder(binding.root){
        val cardView = binding.similarRecipeCardView
        val similarRecipeTitle = binding.similarTitle
        val recipeImage = binding.similarItemImage
        val recipeReadyIn = binding.similarReadyInMinutes
    }

    class DiffUtilComparator : DiffUtil.ItemCallback<SimilarRecipeItemItem>(){
        override fun areItemsTheSame(
            oldItem: SimilarRecipeItemItem,
            newItem: SimilarRecipeItemItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SimilarRecipeItemItem,
            newItem: SimilarRecipeItemItem
        ): Boolean {
            return oldItem == newItem
        }

    }


}