package com.example.marvel.presentation.characterDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.marvel.databinding.FragmentCharacterDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailsBinding
    private val characterDetailsViewModel: CharacterDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.characterDetailsViewModel = characterDetailsViewModel
        binding.lifecycleOwner = this

        arguments?.let {
            val args = CharacterDetailFragmentArgs.fromBundle(it)
            val charId: Int = args.characterId
            characterDetailsViewModel.getCharacterDetails(charId)
        }

        observerError()
        observerBackClick()
    }

    private fun observerError() {
        characterDetailsViewModel.errorMessage.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                Toast.makeText(
                    this@CharacterDetailFragment.requireContext(),
                    it,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun observerBackClick(){
        characterDetailsViewModel.backClick.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
            }
        }
    }
}