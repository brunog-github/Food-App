package com.example.foodapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.foodapp.util.Constants.API_KEY
import com.example.foodapp.util.Constants.QUERY_ADD_RECIPE_INFORMATION
import com.example.foodapp.util.Constants.QUERY_APIKEY
import com.example.foodapp.util.Constants.QUERY_DIET
import com.example.foodapp.util.Constants.QUERY_FILL_INGREDIENTS
import com.example.foodapp.util.Constants.QUERY_NUMBER
import com.example.foodapp.util.Constants.QUERY_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    application: Application
): AndroidViewModel(application) {

     fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[QUERY_NUMBER] = "50"
        queries[QUERY_APIKEY] = API_KEY
        queries[QUERY_TYPE] = "snack"
        queries[QUERY_DIET] = "vegan"
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }
}