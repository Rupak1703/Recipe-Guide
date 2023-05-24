package com.example.recipe_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipe_app.adapters.IngredientsRVAdapter
import com.example.recipe_app.adapters.InstructionsAdapter
import com.example.recipe_app.adapters.SimilarRecipeAdapter
import com.example.recipe_app.databinding.ActivityRecipeDetailsBinding
import com.example.recipe_app.viewmodels.RecipeViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailsBinding
    private lateinit var randomRecipeViewModel: RecipeViewModel
    private lateinit var ingredientsRVAdapter: IngredientsRVAdapter
    private lateinit var similarRecipeAdapter: SimilarRecipeAdapter
    private lateinit var instructionAdapter: InstructionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /// initializers
        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        randomRecipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)
        ingredientsRVAdapter = IngredientsRVAdapter(this)
        similarRecipeAdapter = SimilarRecipeAdapter(this)
        instructionAdapter = InstructionsAdapter(this)

        setContentView(binding.root)

        /// RECEIVING VALUES FROM THE ADAPTER
        val recipeID = intent.getStringExtra("recipeID")

        if (recipeID != null) {


            /// FOR FETCHING THE RECIPE DETAILS ///
            randomRecipeViewModel.getRecipeDetails(recipeID.toInt())


            randomRecipeViewModel.recipeDetailsLiveData.observe(this , Observer {
                binding.recipeName.text = it.title
                binding.recipeSource.text =  Html.fromHtml("<b>source: </b>" + it.sourceName)
                Picasso.get().load(it.image).placeholder(R.drawable.ic_launcher_foreground).into(binding.recipeImage)
                binding.recipeSummary.text = Html.fromHtml("<b>Summary: </b>" + it.summary)

                /// SETTING UP THE RECYCLER VIEW ///
                binding.recyclerIngredients.apply {
                    layoutManager = LinearLayoutManager(this@RecipeDetailsActivity , LinearLayoutManager.HORIZONTAL , false)
                    setHasFixedSize(true)
                    adapter = ingredientsRVAdapter
                }
                ingredientsRVAdapter.submitList(it.extendedIngredients)
            })



            /// FOR FETCHING THE SIMILAR RECIPES VIA VIEW_MODEL
            randomRecipeViewModel.getSimilarRecipeDetails(recipeID.toInt())

            randomRecipeViewModel.similarRecipeLiveData.observe(this , Observer {
                /// setting up the new recycler view ///
                binding.recyclerSimilar.apply {
                    layoutManager = LinearLayoutManager(this@RecipeDetailsActivity , LinearLayoutManager.HORIZONTAL , false)
                    setHasFixedSize(true)
                    adapter = similarRecipeAdapter
                }
                similarRecipeAdapter.submitList(it.toList())
            })




            /// FETCHING THE INSTRUCTIONS FOR THE PARTICULAR RECIPE FROM THE API THRU VIEW-MODEL ///
            randomRecipeViewModel.getDetailedAnalysisRecipe(recipeID.toInt())

            randomRecipeViewModel.detailedAnalyzedRecipeLiveData.observe(this , Observer {
                /// setting up the recycler view
                binding.recipeInstructions.apply {
                    layoutManager = LinearLayoutManager(this@RecipeDetailsActivity)
                    setHasFixedSize(true)
                    adapter = instructionAdapter
                }
                instructionAdapter.submitList(it.toList())
            })

        }


    }
}