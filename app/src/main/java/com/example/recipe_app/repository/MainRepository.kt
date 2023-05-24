package com.example.recipe_app.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipe_app.models.DetailedAnalyzedRecipe
import com.example.recipe_app.models.RandomRecipesItem
import com.example.recipe_app.models.RecipeDetailsItem
import com.example.recipe_app.models.SimilarRecipeItem
import com.example.recipe_app.retrofit.APiService
import com.example.recipe_app.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MainRepository @Inject constructor(private val aPiService: APiService) {

    /// GETTING SELECTED TAGGED RECIPE FROM THE API ///
    private val _responseRandomRecipe = MutableLiveData<RandomRecipesItem>()

    val responseRandomRecipe : LiveData<RandomRecipesItem>
    get() = _responseRandomRecipe


    suspend fun getAllSelectedTagRecipe(tags : List<String>){
        val result = aPiService.getAllSelectedTagRecipes(5 , tags , Constants.API_KEY)

        if (result.isSuccessful && result.body() != null){
            Log.d("MAIN", "getAllSelectedTagResponses: ${result.body()} -> data accessed successfully")
            _responseRandomRecipe.postValue(result.body())
        } else {
            Log.d("MAIN", "empty list -> some error occurred to GET data from the API and result = ${result.body()}")
        }
    }




    /// GETTING DETAILS OF THE RECIPE W.R.T ID
    private val _recipeDetailsItem = MutableLiveData<RecipeDetailsItem>()

    val responseRecipeDetails : LiveData<RecipeDetailsItem>
    get() = _recipeDetailsItem

    suspend fun getRecipeDetails(recipeID : Int){
        val result = aPiService.getRecipeDetailForID(recipeID , Constants.API_KEY)

        if (result.isSuccessful && result.body() != null){
            Log.d("MAIN", "getRecipeDetails: ${result.body()} -> data accessed successfully")
            _recipeDetailsItem.postValue(result.body())
        } else {
            Log.d("MAIN", "getRecipeDetails -> some error occurred to GET data from the API and result = ${result.body()}")
        }
    }





    /// GET SIMILAR RECIPE W.R.T SOURCE RECIPE ID ///
    private val _similarRecipeItem = MutableLiveData<SimilarRecipeItem>()

    val similarRecipeItem : LiveData<SimilarRecipeItem>
    get() = _similarRecipeItem

    suspend fun getSimilarRecipeDetails(sourceRecipeID : Int){
        val result = aPiService.getSimilarRecipes(sourceRecipeID , 5  ,  Constants.API_KEY)

        if (result.isSuccessful && result.body() != null){
            Log.d("MAIN", "SimilarRecipe: ${result.body()} -> data accessed successfully")
            _similarRecipeItem.postValue(result.body())
        } else {
            Log.d("MAIN", "SimilarRecipe -> some error occurred to GET data from the API and result = ${result.body()}")
        }
    }




    /// GETTING THE DETAILED ANALYSIS RECIPE STEP BY STEP ///
    private val _detailedRecipeAnalysis = MutableLiveData<DetailedAnalyzedRecipe>()

    val detailedRecipeAnalysis : LiveData<DetailedAnalyzedRecipe>
    get() = _detailedRecipeAnalysis

    suspend fun getDetailedRecipeAnalysis(recipeID : Int){
        val result = aPiService.getDetailedRecipeInstruction(recipeID , Constants.API_KEY)

        if (result.isSuccessful && result.body() != null){
            Log.d("MAIN", "detailedRecipeAnalysis: ${result.body()} -> data accessed successfully")
            _detailedRecipeAnalysis.postValue(result.body())
        } else {
            Log.d("MAIN", "detailedRecipeAnalysis -> some error occurred to GET data from the API and result = ${result.body()}")
        }
    }
}