package com.example.foodapp.ui.fragments.favorites

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.adapters.FavoriteRecipeAdapter
import com.example.foodapp.databinding.FragmentFavoritesRecipesBinding
import com.example.foodapp.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesRecipesFragment : Fragment() {

    private var _binding: FragmentFavoritesRecipesBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()
    private val adapter: FavoriteRecipeAdapter by lazy {
        FavoriteRecipeAdapter(requireActivity(), mainViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoritesRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        binding.adapter = adapter

        setHasOptionsMenu(true)

        setupRecyclerView(binding.favoritesRecyclerView)

        return binding.root
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_recipes_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_all_recipes_menu) {
            mainViewModel.deleteAllFavoriteRecipes()
            showSnackBar("All recipes removed.")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).setAction("OK"){}.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter.clearContextualActionMode()
    }
}