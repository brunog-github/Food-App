package com.example.foodapp.util.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.foodapp.data.database.models.RecipesEntity
import com.example.foodapp.models.FoodRecipe
import com.example.foodapp.util.NetworkResult

object RecipesBinding {

    @BindingAdapter("android:readApiResponse", "android:readDatabase", requireAll = true)
    @JvmStatic
    fun errorImageVisibility(
        view: ImageView,
        apiResponse: NetworkResult<FoodRecipe>?,
        database: List<RecipesEntity>?
    ) {
        if (apiResponse is NetworkResult.Error && database.isNullOrEmpty()) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.INVISIBLE
        }
    }

    @BindingAdapter("android:readApiResponse2", "android:readDatabase2", requireAll = true)
    @JvmStatic
    fun errorTextViewVisibility(
        view: TextView,
        apiResponse: NetworkResult<FoodRecipe>?,
        database: List<RecipesEntity>?
    ) {
        if (apiResponse is NetworkResult.Error && database.isNullOrEmpty()) {
            view.visibility = View.VISIBLE
            view.text = apiResponse.message.toString()
        } else {
            view.visibility = View.INVISIBLE
        }
    }
}