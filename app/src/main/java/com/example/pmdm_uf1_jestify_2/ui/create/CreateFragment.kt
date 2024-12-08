package com.example.pmdm_uf1_jestify_2.ui.create

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.pmdm_uf1_jestify_2.R
import com.example.pmdm_uf1_jestify_2.databinding.FragmentCreateBinding
import com.example.pmdm_uf1_jestify_2.jokeAPI.Joke
import com.google.android.material.chip.Chip

class CreateFragment : Fragment() {

    private var _binding: FragmentCreateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val createViewModel = ViewModelProvider(requireActivity()).get(CreateViewModel::class.java)

        _binding = FragmentCreateBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.btnCreateJoke.setOnClickListener {
            val setup = binding.editTextJokeSetup.text.toString()
            val delivery = binding.editTextJokeDelivery.text.toString()
            val category = binding.jokeTypesSpinner.selectedItem.toString()

            val flags = mutableMapOf<String, Boolean>()
            for (i in 0 until binding.chipGroupFlags.childCount) {
                val chip = binding.chipGroupFlags.getChildAt(i) as Chip
                flags[chip.text.toString()] = chip.isChecked
            }

            val joke = Joke(
                error = false,
                category = category,
                type = "twopart",
                joke = null,
                setUp = setup,
                delivery = delivery,
                flags = flags,
                id = 9999,
                safe = true,
                lang = "eng"
            )

            createViewModel.addJoke(joke) // Add joke to viewModel LiveData
            Toast.makeText(requireContext(), "Joke saved!", Toast.LENGTH_SHORT).show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
