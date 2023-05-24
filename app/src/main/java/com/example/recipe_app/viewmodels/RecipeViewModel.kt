package com.example.recipe_app.viewmodels

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.models.DetailedAnalyzedRecipe
import com.example.recipe_app.models.RandomRecipesItem
import com.example.recipe_app.models.RecipeDetailsItem
import com.example.recipe_app.models.SimilarRecipeItem
import com.example.recipe_app.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    val randomRecipeLiveData : LiveData<RandomRecipesItem>
    get() = repository.responseRandomRecipe


    /// this init change is not tested in actual app
//    init {
//        val initialList = listOf<String>("main course")
//        viewModelScope.launch {
//            repository.getAllSelectedTagRecipe(initialList)
//        }
//    }



    /// GET RECIPE BY SELECTED TAG ///
    fun getSelectedTagRecipe(tags : List<String>){
        viewModelScope.launch {
            repository.getAllSelectedTagRecipe(tags)
        }
    }


    /// GET RECIPE BY ID ///
    val recipeDetailsLiveData : LiveData<RecipeDetailsItem>
    get() = repository.responseRecipeDetails

    fun getRecipeDetails(recipeID : Int){
        viewModelScope.launch {
            repository.getRecipeDetails(recipeID)
        }
    }


    /// GET SIMILAR RECIPE W.R.T SOURCE ID ///
    val similarRecipeLiveData : LiveData<SimilarRecipeItem>
    get() = repository.similarRecipeItem

    fun getSimilarRecipeDetails(sourceRecipeID : Int){
        viewModelScope.launch {
            repository.getSimilarRecipeDetails(sourceRecipeID)
        }
    }


    /// GET DETAILED RECIPE ANALYSIS STEP BY STEP ///
    val detailedAnalyzedRecipeLiveData : LiveData<DetailedAnalyzedRecipe>
    get() = repository.detailedRecipeAnalysis

    fun getDetailedAnalysisRecipe(recipeID : Int){
        viewModelScope.launch {
            repository.getDetailedRecipeAnalysis(recipeID)
        }
    }

}