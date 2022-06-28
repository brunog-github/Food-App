package com.example.foodapp.util.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.example.foodapp.R

object RecipesRowBinding {

    @BindingAdapter("android:loadImageFromUrl")
    @JvmStatic
    fun loadImageFromUrl(view: ImageView, value: String) {
        view.load(data = value) {
            crossfade(600)
            error(R.drawable.ic_error_placeholder)
        }
    }

    @BindingAdapter("android:setNumberOfLikes")
    @JvmStatic
    fun setNumberOfLikes(view: TextView, value: Int) {
        view.text = value.toString()
    }

    @BindingAdapter("android:setNumberOfMinutes")
    @JvmStatic
    fun setNumberOfMinutes(view: TextView, value: Int) {
        view.text = value.toString()
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
}