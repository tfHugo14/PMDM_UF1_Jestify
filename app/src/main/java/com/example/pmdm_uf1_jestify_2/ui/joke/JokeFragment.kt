package com.example.pmdm_uf1_jestify_2.ui.joke

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.pmdm_uf1_jestify_2.R
import com.example.pmdm_uf1_jestify_2.databinding.FragmentJokeBinding
import com.example.pmdm_uf1_jestify_2.jokeAPI.Joke
import com.example.pmdm_uf1_jestify_2.jokeAPI.JokeDAO
import com.example.pmdm_uf1_jestify_2.ui.create.CreateViewModel

class JokeFragment : Fragment() {

    private var _binding: FragmentJokeBinding? = null
    private val binding get() = _binding!!

    // Declare ViewModel variables at the class level
    private lateinit var jokeViewModel: JokeViewModel
    private lateinit var createViewModel: CreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize ViewModels
        val jokeDAO = JokeDAO()
        jokeViewModel = ViewModelProvider(this, JokeViewModelFactory(jokeDAO)).get(JokeViewModel::class.java)
        createViewModel = ViewModelProvider(requireActivity()).get(CreateViewModel::class.java)

        // Set the jokeType if it hasn't been set
        if (jokeViewModel.jokeType.value == null) {
            val jokeType = arguments?.let { JokeFragmentArgs.fromBundle(it).jokeType }
            jokeType?.let { jokeViewModel.setJokeType(it) }
        }

        // Observe Live Data's
        jokeViewModel.jokeType.observe(viewLifecycleOwner) { jokeType ->
            binding.textJoke.text = jokeType
        }
        jokeViewModel.jokeContent.observe(viewLifecycleOwner) { jokeContent ->
            binding.contentJoke.text = jokeContent
        }

        // Bookmark logic
        val btnSetBookmark: ImageButton = root.findViewById(R.id.btn_set_bookmark)
        btnSetBookmark.setOnClickListener {
            btnSetBookmark.isSelected = !btnSetBookmark.isSelected

            if (btnSetBookmark.isSelected) {
                val lastJoke = jokeViewModel.lastFetchedJoke.value
                if (lastJoke != null) {
                    val bookmarkedJoke = Joke(
                        error = lastJoke.error,
                        category = lastJoke.category,
                        type = lastJoke.type,
                        joke = lastJoke.joke,
                        setUp = lastJoke.setUp,
                        delivery = lastJoke.delivery,
                        flags = lastJoke.flags,
                        id = lastJoke.id ?: 9999,
                        safe = lastJoke.safe,
                        lang = lastJoke.lang
                    )
                    createViewModel.addJoke(bookmarkedJoke)
                    Toast.makeText(requireContext(), "Joke bookmarked!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "No joke to bookmark!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        // Star rating logic
        val starList = listOf(
            root.findViewById<ImageButton>(R.id.btn_set_stars_1),
            root.findViewById<ImageButton>(R.id.btn_set_stars_2),
            root.findViewById<ImageButton>(R.id.btn_set_stars_3),
            root.findViewById<ImageButton>(R.id.btn_set_stars_4),
            root.findViewById<ImageButton>(R.id.btn_set_stars_5)
        )
        starList.forEachIndexed { index, button ->
            button.setOnClickListener {
                for (i in 0..index) starList[i].isSelected = true
                for (i in index + 1 until starList.size) starList[i].isSelected = false
            }
        }

        // New Joke Button
        val btnCreateJoke: Button = root.findViewById(R.id.btn_create_joke)
        btnCreateJoke.setOnClickListener {
            if (btnSetBookmark.isSelected) {
                btnSetBookmark.isSelected = false
            }
            starList.forEach { it.isSelected = false }
            jokeViewModel.fetchJokeContent(jokeViewModel.jokeType.value ?: "")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}