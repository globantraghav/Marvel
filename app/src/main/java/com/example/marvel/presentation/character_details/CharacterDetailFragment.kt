package com.example.marvel.presentation.character_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.marvel.databinding.FragmentCharacterDetailsBinding
import com.example.marvel.domain.model.CharacterDetail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class CharacterDetailFragment:Fragment() {

    private lateinit var binding: FragmentCharacterDetailsBinding
    private val characterDetailsViewModel: CharacterDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val args = CharacterDetailFragmentArgs.fromBundle(it)
            val charId:Int = args.characterId
            characterDetailsViewModel.getCharacterDetails(charId)
        }

        observeViewModel()

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun observeViewModel(){
        lifecycle.coroutineScope.launchWhenCreated {
            characterDetailsViewModel.characterDetails.observe(viewLifecycleOwner) { it ->
                if (it.isLoading) {
                }
                if (it.error.isNotBlank()) {
                    Toast.makeText(
                        this@CharacterDetailFragment.requireContext(),
                        it.error,
                        Toast.LENGTH_LONG
                    ).show()
                }
                if (it.characterDetails!=null) {
                    setUI(it.characterDetails)
                }
            }
            delay(1000)
        }
    }

    private fun setUI(characterDetail: CharacterDetail?){
        val url = "${characterDetail?.thumbnail?.replace("http","https")}/portrait_xlarge.${characterDetail?.thumbnailExt}"

        binding.tvCharacterName.text = characterDetail?.name
        binding.tvCharacterDescription.text = characterDetail?.description

        Glide.with(this)
            .load(url)
            .into(binding.ivCharacter)

    }

}