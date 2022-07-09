package com.example.foodapp.adapters

import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.data.database.models.FavoriteEntity
import com.example.foodapp.databinding.FavoriteRecipesRowLayoutBinding
import com.example.foodapp.ui.fragments.favorites.FavoritesRecipesFragmentDirections
import com.example.foodapp.util.RecipesDiffUtil
import com.example.foodapp.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar

class FavoriteRecipeAdapter(
    private val requireActivity: FragmentActivity,
    private val mainViewModel: MainViewModel
): RecyclerView.Adapter<FavoriteRecipeAdapter.MyViewHolder>(), ActionMode.Callback {

    private var multiSelection = false
    private var selectedRecipes = arrayListOf<FavoriteEntity>()
    private var myViewHolders = arrayListOf<MyViewHolder>()
    private lateinit var actionMode: ActionMode
    private lateinit var rootView: View

    private var recipesList = emptyList<FavoriteEntity>()

    class MyViewHolder(
        val binding: FavoriteRecipesRowLayoutBinding
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
        myViewHolders.add(holder)
        rootView = holder.itemView.rootView

        val currentRecipe = recipesList[position]
        holder.bind(currentRecipe)

        /**
         * Single Click Listener
         * */
        holder.binding.favoriteRecipesRowLayout.setOnClickListener {
            if (multiSelection) {
                applySelection(holder, currentRecipe)
            } else {
                val action =
                    FavoritesRecipesFragmentDirections.actionFavoritesRecipesFragmentToDetailsActivity(
                        currentRecipe.result
                    )
                holder.itemView.findNavController().navigate(action)
            }
        }

        /**
         * Long Click Listener
         * */
        holder.binding.favoriteRecipesRowLayout.setOnLongClickListener {
            if (!multiSelection) {
                multiSelection = true
                requireActivity.startActionMode(this)
                applySelection(holder, currentRecipe)
                true
            } else {
                applySelection(holder, currentRecipe)
               true
            }
        }
    }

    private fun applySelection(holder: MyViewHolder, currentRecipe: FavoriteEntity) {
        if (selectedRecipes.contains(currentRecipe)) {
            selectedRecipes.remove(currentRecipe)
            changeRecipeStyle(holder, R.color.card_background_color, R.color.stroke_color)
            applyActionModeTitle()
        } else {
            selectedRecipes.add(currentRecipe)
            changeRecipeStyle(holder, R.color.card_background_light_color, R.color.purple_500)
            applyActionModeTitle()
        }
    }

    private fun changeRecipeStyle(holder: MyViewHolder, backgroundColor: Int, strokeColor: Int) {
        holder.binding.favoriteRecipesRowLayout.setBackgroundColor(
            ContextCompat.getColor(requireActivity, backgroundColor)
        )
        holder.binding.favoriteRowCardView.strokeColor =
            ContextCompat.getColor(requireActivity, strokeColor)
    }

    private fun applyActionModeTitle() {
        if (selectedRecipes.isEmpty()) {
            actionMode.finish()
            multiSelection = false
        } else {
            actionMode.title = if (selectedRecipes.size == 1) {
                "${selectedRecipes.size} item selected"
            } else {
                "${selectedRecipes.size} items selected"
            }
        }
    }


    override fun getItemCount() = recipesList.size

    override fun onCreateActionMode(mActionMode: ActionMode?, menu: Menu?): Boolean {
        mActionMode?.menuInflater?.inflate(R.menu.favorite_contextual_menu, menu)
        actionMode = mActionMode!!
        applyStatusBarColor(R.color.contextual_status_bar_color)
        return true
    }

    override fun onPrepareActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
        return true
    }

    override fun onActionItemClicked(actionMode: ActionMode?, menu: MenuItem?): Boolean {
        if (menu?.itemId == R.id.delete_favorite_recipe_menu) {
            selectedRecipes.forEach {
                mainViewModel.deleteFavoriteRecipe(it)
            }
            showSnackBar("${selectedRecipes.size} recipe's removed.")
            multiSelection = false
            selectedRecipes.clear()
            actionMode?.finish()
        }
        return true
    }

    override fun onDestroyActionMode(actionMode: ActionMode?) {
        myViewHolders.forEach { holder ->
            changeRecipeStyle(holder, R.color.card_background_color, R.color.stroke_color)
        }
        multiSelection = false
        selectedRecipes.clear()
        applyStatusBarColor(R.color.status_bar_color)
    }

    private fun applyStatusBarColor(color: Int) {
        requireActivity.window.statusBarColor = ContextCompat.getColor(requireActivity, color)
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).setAction("OK"){}.show()
    }

    fun clearContextualActionMode() {
        if (this::actionMode.isInitialized) actionMode.finish()
    }

    fun setData(newFavoriteRecipes: List<FavoriteEntity>) {
        val recipesDiffUtil = RecipesDiffUtil(recipesList, newFavoriteRecipes)
        val diffResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipesList = newFavoriteRecipes
        diffResult.dispatchUpdatesTo(this)
    }
}