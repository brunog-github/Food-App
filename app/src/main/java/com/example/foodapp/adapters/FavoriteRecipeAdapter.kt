package com.example.foodapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.data.database.models.FavoriteEntity
import com.example.foodapp.databinding.FavoriteRecipesRowLayoutBinding
import com.example.foodapp.util.RecipesDiffUtil

class FavoriteRecipeAdapter: RecyclerView.Adapter<FavoriteRecipeAdapter.MyViewHolder>() {

    private var recipesList = emptyList<FavoriteEntity>()

    class MyViewHolder(
       private val binding: FavoriteRecipesRowLayoutBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(favoriteEntity: FavoriteEntity) {
            binding.favoritesEntity = favoriteEntity
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(FavoriteRecipesRowLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val list = recipesList[position]
        holder.bind(list)
    }

    override fun getItemCount() = recipesList.size

    fun setData(newFavoriteRecipes: List<FavoriteEntity>) {
        val recipesDiffUtil = RecipesDiffUtil(recipesList, newFavoriteRecipes)
        val diffResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipesList = newFavoriteRecipes
        diffResult.dispatchUpdatesTo(this)
    }
}