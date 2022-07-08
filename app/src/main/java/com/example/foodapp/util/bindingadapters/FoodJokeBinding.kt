package com.example.foodapp.util.bindingadapters

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.foodapp.data.database.models.FoodJokeEntity
import com.example.foodapp.models.FoodJoke
import com.example.foodapp.util.NetworkResult
import com.google.android.material.card.MaterialCardView

object FoodJokeBinding {

    @BindingAdapter("android:readApiResponse3", "android:readDatabase3", requireAll = false)
    @JvmStatic
    fun setCardAndProgressVisibility(
        view: View,
        apiResponse: NetworkResult<FoodJoke>?,
        database: List<FoodJokeEntity>?
    ) {
        when(apiResponse) {
            is NetworkResult.Loading -> {
                when(view) {
                    is ProgressBar -> view.visibility = View.VISIBLE
                    is MaterialCardView -> view.visibility = View.INVISIBLE
                }
            }
            is NetworkResult.Error -> {
                when(view) {
                    is ProgressBar -> view.visibility = View.INVISIBLE
                    is MaterialCardView -> {
                        view.visibility = View.VISIBLE
                        if (database != null && database.isEmpty()) {
                            view.visibility = View.INVISIBLE
                        }
                    }
                }
            }
            is NetworkResult.Success -> {
                when(view) {
                    is ProgressBar -> view.visibility = View.INVISIBLE
                    is MaterialCardView -> view.visibility = View.VISIBLE
                }
            }
            else -> {}
        }
    }

    @BindingAdapter("android:readApiResponse4", "android:readDatabase4", requireAll = true)
    @JvmStatic
    fun setErrorViewsVisibility(
        view: View,
        apiResponse: NetworkResult<FoodJoke>?,
        database: List<FoodJokeEntity>?
    ) {
        if (database != null && database.isEmpty() && apiResponse != null) {
            view.visibility = View.VISIBLE
            if (view is TextView) view.text = apiResponse.message.toString()

        } else if (apiResponse is NetworkResult.Success) {
            view.visibility = View.INVISIBLE
        }

    }
}