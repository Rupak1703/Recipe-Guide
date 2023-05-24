package com.example.recipe_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recipe_app.databinding.RecipeInstructionsRvBinding
import com.example.recipe_app.databinding.SimilarRecipeItemBinding
import com.example.recipe_app.models.DetailedAnalyzedRecipe
import com.example.recipe_app.models.DetailedAnalyzedRecipeItem
import com.example.recipe_app.models.SimilarRecipeItemItem

class InstructionsAdapter(private val context : Context): ListAdapter<DetailedAnalyzedRecipeItem , InstructionsAdapter.InstructionAdapterViewHolder>(DiffUtilComparator()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionAdapterViewHolder {
        val view = RecipeInstructionsRvBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return InstructionAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: InstructionAdapterViewHolder, position: Int) {
        val currItem = getItem(position)

        holder.instructName.text = currItem.name

        /// code for the recycler view
        val instructionStepAdapter = InstructionStepAdapter(context)
        holder.instructRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context , LinearLayoutManager.VERTICAL , false)
            adapter = instructionStepAdapter
        }
        instructionStepAdapter.submitList(currItem.steps)
    }

    inner class InstructionAdapterViewHolder(private val binding: RecipeInstructionsRvBinding) : ViewHolder(binding.root){
        val instructName = binding.instructionName
        val instructRecyclerView = binding.recyclerInstructionSteps
    }

    class DiffUtilComparator : DiffUtil.ItemCallback<DetailedAnalyzedRecipeItem>(){
        override fun areItemsTheSame(
            oldItem: DetailedAnalyzedRecipeItem,
            newItem: DetailedAnalyzedRecipeItem
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: DetailedAnalyzedRecipeItem,
            newItem: DetailedAnalyzedRecipeItem
        ): Boolean {
            return oldItem == newItem
        }
    }


}