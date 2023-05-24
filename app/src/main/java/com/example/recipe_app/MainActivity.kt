package com.example.recipe_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipe_app.adapters.RandomRecipeAdapter
import com.example.recipe_app.databinding.ActivityMainBinding
import com.example.recipe_app.viewmodels.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var randomRecipeViewModel: RecipeViewModel
    private lateinit var randomRecipeAdapter: RandomRecipeAdapter
    private lateinit var spinner: Spinner

    private lateinit var tagsSpinner : MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /// INITIALIZER
        binding = ActivityMainBinding.inflate(layoutInflater)
        randomRecipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)
        spinner = binding.spinnerTags
        randomRecipeAdapter = RandomRecipeAdapter(this)
        tagsSpinner = mutableListOf()
        setContentView(binding.root)


        /// SETTING UP THE SPINNER ///
        val arrayAdapter =
            ArrayAdapter.createFromResource(this, R.array.tags, R.layout.spinner_text_item)

        /// setting up drop-down resource layout for our spinner ///
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = spinnerListener() /// we can use flows to solve this function re running with the change in layout



        /// OBSERVER ///
        randomRecipeViewModel.randomRecipeLiveData.observe(this, Observer {
            binding.recyclerViewRandomRecipe.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
                adapter = randomRecipeAdapter
            }
            randomRecipeAdapter.submitList(it.recipes)
        })


        /// SEARCH VIEW LISTENER ///
        binding.searchViewMain.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                tagsSpinner.clear()
                if (query != null) {
                    tagsSpinner.add(query)
                    randomRecipeViewModel.getSelectedTagRecipe(tagsSpinner)

                }  else {
                    Toast.makeText(this@MainActivity, "Empty Search Query", Toast.LENGTH_SHORT).show()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }




    //// PRIVATE FUNCTIONS ////

    private fun spinnerListener() : OnItemSelectedListener{
        /// adding select listener for the spinner ///
        val listener = object : OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                /// tags which will be added in the list when the user selects some items from the spinner
                tagsSpinner.clear()
                if (adapterView != null) {
                    tagsSpinner.add(adapterView.selectedItem.toString())

                    randomRecipeViewModel.getSelectedTagRecipe(tagsSpinner)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        return listener
    }


}