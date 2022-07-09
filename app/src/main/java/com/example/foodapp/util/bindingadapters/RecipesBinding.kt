package com.example.foodapp.util.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.room.RoomDatabase
import com.example.foodapp.data.database.models.RecipesEntity
import com.example.foodapp.models.FoodRecipe
import com.example.foodapp.util.NetworkResult

object RecipesBinding {

    @BindingAdapter("android:readApiResponse", "android:readDatabase", requireAll = true)
    @JvmStatic
    fun handleReadDataErrors(
        view: View,
        apiResponse: NetworkResult<FoodRecipe>?,
        database: List<RecipesEntity>?
    ) {
        when(view) {
            is ImageView -> {
                view.isVisible = apiResponse is NetworkResult.Error && database.isNullOrEmpty()
            }
            is TextView -> {
                view.isVisible = apiResponse is NetworkResult.Error && database.isNullOrEmpty()
                view.text = apiResponse?.message.toString()
            }
        }
    }
}