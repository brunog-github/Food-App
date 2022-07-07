package com.example.foodapp.util.bindingadapters

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.adapters.FavoriteRecipeAdapter
import com.example.foodapp.data.database.models.FavoriteEntity

object FavoriteRecipesBinding {

    @BindingAdapter("android:viewVisibility", "android:setData", requireAll = false)
    @JvmStatic
    fun setDataAndViewVisibility(
        view: View,
        favoriteEntity: List<FavoriteEntity>?,
        adapter: FavoriteRecipeAdapter?
    ) {
        if(favoriteEntity.isNullOrEmpty()) {
            when(view) {
                is RecyclerView -> view.visibility = View.INVISIBLE
                else -> view.visibility = View.VISIBLE
            }
        } else {
            when(view) {
                is RecyclerView -> {
                    view.visibility = View.VISIBLE
                    adapter?.setData(favoriteEntity)
                }
                else -> view.visibility = View.INVISIBLE
            }
        }
    }
}