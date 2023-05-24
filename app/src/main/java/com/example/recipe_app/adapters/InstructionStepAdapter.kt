package com.example.recipe_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recipe_app.databinding.InstructionsStepsRvBinding
import com.example.recipe_app.models.StepX

class InstructionStepAdapter(private val context : Context) : ListAdapter<StepX , InstructionStepAdapter.InstructionStepAdapterViewHolder>(DIffUtilComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionStepAdapterViewHolder {
        val view = InstructionsStepsRvBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return InstructionStepAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: InstructionStepAdapterViewHolder, position: Int) {
        val currItem = getItem(position)

        holder.instructionStepNo.text = currItem.number.toString()
        holder.instructionStepName.text = currItem.step

        /// SETTING UP THE RECYCLER VIEW

        val instructionIngredientsAdapter = InstructionIngredientsAdapter(context)
        holder.ingredientsRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context , LinearLayoutManager.HORIZONTAL , false)
            adapter = instructionIngredientsAdapter
        }

        if (currItem.ingredients.isEmpty()){
            holder.textIngredients.visibility = View.GONE
        } else {
            instructionIngredientsAdapter.submitList(currItem.ingredients)
        }
    }


    inner class InstructionStepAdapterViewHolder(private val binding: InstructionsStepsRvBinding) : ViewHolder(binding.root){
        val instructionStepNo = binding.instructionStepNumber
        val instructionStepName = binding.instructionStepTitle
        val ingredientsRecyclerView = binding.recyclerIngredients
        val textIngredients = binding.textIngredients
    }

    class DIffUtilComparator : DiffUtil.ItemCallback<StepX>(){
        override fun areItemsTheSame(oldItem: StepX, newItem: StepX): Boolean {
            return oldItem.number == newItem.number
        }

        override fun areContentsTheSame(oldItem: StepX, newItem: StepX): Boolean {
            return oldItem == newItem
        }
    }

}