package com.example.recipe_app.retrofit

import com.example.recipe_app.models.DetailedAnalyzedRecipe
import com.example.recipe_app.models.RandomRecipesItem
import com.example.recipe_app.models.RecipeDetailsItem
import com.example.recipe_app.models.SimilarRecipeItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
interface APiService {
    /// GET METHOD FOR SELECTED MENU RECIPES FROM THE API ///
    @Headers("Content-Type:application/json")
    @GET("/recipes/random")
    suspend fun getAllSelectedTagRecipes(@Query("number") number : Int, @Query("tags") tags : List<String> , @Query("apiKey") apiKey : String) : Response<RandomRecipesItem>


    /// GET INFORMATION FOR PARTICULAR ID OF THE RECIPE ///
    @Headers("Content-Type:application/json")
    @GET("/recipes/{id}/information")
    suspend fun getRecipeDetailForID(@Path("id") id : Int , @Query("apiKey") apiKey : String) : Response<RecipeDetailsItem>


    /// GET SIMILAR RECIPES ///
    @Headers("Content-Type:application/json")
    @GET("/recipes/{id}/similar")
    suspend fun getSimilarRecipes(@Path("id") id : Int , @Query("number") number : Int , @Query("apiKey") apiKey : String) : Response<SimilarRecipeItem>


    /// GET DETAIL RECIPE INSTRUCTION ///
    @Headers("Content-Type:application/json")
    @GET("/recipes/{id}/analyzedInstructions")
    suspend fun getDetailedRecipeInstruction(@Path("id") id : Int , @Query("apiKey") apiKey : String) : Response<DetailedAnalyzedRecipe>
}