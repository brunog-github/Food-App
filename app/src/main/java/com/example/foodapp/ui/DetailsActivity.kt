package com.example.foodapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.example.foodapp.adapters.PagerAdapter
import com.example.foodapp.databinding.ActivityDetailsBinding
import com.example.foodapp.ui.fragments.details.IngredientsFragment
import com.example.foodapp.ui.fragments.details.InstructionsFragment
import com.example.foodapp.ui.fragments.details.OverviewFragment
import com.google.android.material.tabs.TabLayoutMediator

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val args by navArgs<DetailsActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())
        fragments.add(InstructionsFragment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")
        titles.add("Instructions")

        val resultBundle = Bundle()
        resultBundle.putParcelable("recipeBundle", args.result)

        val pagerAdapter = PagerAdapter(
            resultBundle,
            fragments,
            this
        )

        binding.viewPager2.adapter = pagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}