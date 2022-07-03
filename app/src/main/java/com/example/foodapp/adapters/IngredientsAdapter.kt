package com.example.foodapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foodapp.R
import com.example.foodapp.databinding.IngredientsRowLayoutBinding
import com.example.foodapp.models.ExtendedIngredient
import com.example.foodapp.util.Constants.BASE_IMAGE_URL
import com.example.foodapp.util.RecipesDiffUtil
import com.example.foodapp.util.capitalized

class IngredientsAdapter: RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>() {

    private var ingredientsList = emptyList<ExtendedIngredient>()

    inner class MyViewHolder(
       val binding: IngredientsRowLayoutBinding
    ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(IngredientsRowLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val list = ingredientsList[position]
        holder.binding.apply {
            ingredientImageView.load(BASE_IMAGE_URL + list.image) {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
            ingredientName.text = list.name.capitalized()
            ingredientAmount.text = list.amount.toString()
            ingredientUnit.text = list.unit
            ingredientConsistency.text = list.consistency
            ingredientOriginal.text = list.original
        }
    }

    override fun getItemCount() = ingredientsList.size

    fun setData(newIngredients: List<ExtendedIngredient>) {
        val ingredientsDiffUtil = RecipesDiffUtil(ingredientsList, newIngredients)
        val diffResult = DiffUtil.calculateDiff(ingredientsDiffUtil)
        ingredientsList = newIngredients
        diffResult.dispatchUpdatesTo(this)
    }
}