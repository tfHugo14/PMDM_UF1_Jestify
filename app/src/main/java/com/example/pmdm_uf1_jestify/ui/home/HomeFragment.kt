package com.example.pmdm_uf1_jestify.ui.home

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pmdm_uf1_jestify.databinding.FragmentHomeBinding
import com.example.pmdm_uf1_jestify.ui.joke.JokeFragment


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var btnProgramming: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        btnProgramming = binding.btnProgramming

        val navController = findNavController()

        btnProgramming.setOnClickListener {
            //navController.navigate(R.id.ac)
        }

        homeViewModel.text.observe(viewLifecycleOwner) {}
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}