package com.example.pmdm_uf1_jestify_2.ui.joke

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.pmdm_uf1_jestify_2.R
import com.example.pmdm_uf1_jestify_2.databinding.FragmentJokeBinding
import com.example.pmdm_uf1_jestify_2.ui.favourites.FavouritesViewModel

class JokeFragment : Fragment() {

    private var _binding: FragmentJokeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val jokeViewModel = ViewModelProvider(this).get(JokeViewModel::class.java) // initialize JokeViewModel

        val jokeType = arguments?.let { JokeFragmentArgs.fromBundle(it).jokeType } // retrieve arg from nav bundle

        jokeType?.let { jokeViewModel.setJokeType(it) } // send jokeType to JokeViewModel

        jokeViewModel.jokeType.observe(viewLifecycleOwner) { // observe LiveData text to update the textView
                jokeType ->
            binding.textJoke.text = jokeType
        }

        val jokeContent = "";

        jokeViewModel.setJokeContent(jokeContent)
        jokeViewModel.jokeContent.observe(viewLifecycleOwner) { jokeContent ->
            binding.contentJoke.text = jokeContent
        }

        val btnSetBookmark: ImageButton = root.findViewById(R.id.btn_set_bookmark)
        btnSetBookmark.setOnClickListener {
            btnSetBookmark.isSelected = !btnSetBookmark.isSelected
        }

        // 5 star rating logic
        val starList = listOf(
            root.findViewById<ImageButton>(R.id.btn_set_stars_1),
            root.findViewById<ImageButton>(R.id.btn_set_stars_2),
            root.findViewById<ImageButton>(R.id.btn_set_stars_3),
            root.findViewById<ImageButton>(R.id.btn_set_stars_4),
            root.findViewById<ImageButton>(R.id.btn_set_stars_5)
        )
        starList.forEachIndexed { index, button ->
            button.setOnClickListener {
                for (i in 0..index) { starList[i].isSelected = true } // select: from first star on list to the clicked star
                for (i in index + 1 until starList.size) { starList[i].isSelected = false } // deselect: from (clicked star + 1) index to starList max size
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}