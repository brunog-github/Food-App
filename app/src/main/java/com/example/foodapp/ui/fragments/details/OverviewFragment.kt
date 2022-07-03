package com.example.foodapp.ui.fragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import coil.load
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentOverviewBinding
import com.example.foodapp.models.Result
import org.jsoup.Jsoup

class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable("recipeBundle")

        binding.apply {
            mainImageView.load(myBundle?.image)
            titleTextView.text = myBundle?.title
            likesTextView.text = myBundle?.aggregateLikes.toString()
            timeTextView.text = myBundle?.readyInMinutes.toString()

            myBundle?.summary?.let {
                val summary = Jsoup.parse(it).text()
                summaryTextView.text = summary
            }

            myBundle?.apply {
                if (vegetarian) setColorDietsOption(vegetarianImageView, vegetarianTextView)
                if (vegan) setColorDietsOption(veganImageView, veganTextView)
                if (glutenFree) setColorDietsOption(glutenFreeImageView, glutenFreeTextView)
                if (dairyFree) setColorDietsOption(dairyFreeImageView, dairyFreeTextView)
                if (veryHealthy) setColorDietsOption(healthyImageView, healthyTextView)
                if (cheap) setColorDietsOption(cheapImageView, cheapTextView)
            }
        }
        return binding.root
    }

    private fun setColorDietsOption(imageView: ImageView, textView: TextView){
        imageView.setColorFilter(
            ContextCompat.getColor(requireContext(), R.color.green)
        )
        textView.setTextColor(
            ContextCompat.getColor(requireContext(), R.color.green)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}