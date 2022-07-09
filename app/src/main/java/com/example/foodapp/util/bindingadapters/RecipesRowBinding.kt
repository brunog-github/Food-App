package com.example.foodapp.util.bindingadapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.example.foodapp.R
import com.example.foodapp.models.Result
import com.example.foodapp.ui.fragments.recipes.RecipesFragmentDirections
import org.jsoup.Jsoup

object RecipesRowBinding {

    @BindingAdapter("android:onRecipeClickListener")
    @JvmStatic
    fun onRecipeClickListener(layout: ConstraintLayout, result: Result) {
        layout.setOnClickListener {
            try {
                val action =
                    RecipesFragmentDirections.actionRecipesFragmentToDetailsActivity(result)
                layout.findNavController().navigate(action)

            } catch (e: Exception) {
                Log.d("RecipesRowBinding", e.toString())
            }
        }
    }

    @BindingAdapter("android:loadImageFromUrl")
    @JvmStatic
    fun loadImageFromUrl(view: ImageView, value: String) {
        view.load(data = value) {
            crossfade(600)
            error(R.drawable.ic_error_placeholder)
        }
    }

    @BindingAdapter("android:hasVegan")
    @JvmStatic
    fun hasVegan(view: View, value: Boolean) {
        if (value) {
            when(view) {
                is TextView -> {
                    view.setTextColor(ContextCompat.getColor(view.context, R.color.green))
                }
                is ImageView -> {
                    view.setColorFilter(ContextCompat.getColor(view.context, R.color.green))
                }
            }
        }
    }

    @BindingAdapter("android:parseHtml")
    @JvmStatic
    fun parseHtml(textView: TextView, description: String?) {
        if (description != null) {
            val desc = Jsoup.parse(description).text()
            textView.text = desc
        }
    }
}