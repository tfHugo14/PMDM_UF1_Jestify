package com.example.pmdm_uf1_jestify_2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pmdm_uf1_jestify_2.R
import com.example.pmdm_uf1_jestify_2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnProgramming.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToJokeFragment("Programming")
            findNavController().navigate(action)
        }
        binding.btnMiscellanea.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToJokeFragment("Miscellanea")
            findNavController().navigate(action)
        }
        binding.btnDark.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToJokeFragment("Dark")
            findNavController().navigate(action)
        }
        binding.btnPun.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToJokeFragment("Pun")
            findNavController().navigate(action)
        }
        binding.btnSpooky.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToJokeFragment("Spooky")
            findNavController().navigate(action)
        }
        binding.btnChristmas.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToJokeFragment("Christmas")
            findNavController().navigate(action)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}