package com.example.marvel.presentation.characterList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.Constant
import com.example.marvel.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class CharacterListFragment : Fragment(),CharacterClick {

    private lateinit var characterListAdapter: CharacterListAdapter
    private lateinit var layoutManager: GridLayoutManager
    private val characterListViewModel: CharacterListViewModel by viewModels()
    private lateinit var binding: FragmentCharacterListBinding
    private var paginatedValue = Constant.ZERO

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterListAdapter =
            CharacterListAdapter(mutableListOf(),this)
        binding.characterListAdapter = characterListAdapter
        layoutManager = GridLayoutManager(this@CharacterListFragment.requireContext(), 3)

        binding.rvCharacterList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 1) {
                    paginatedValue += Constant.paginatedValue
                    characterListViewModel.getCharactersList(paginatedValue)
                    observeList()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        paginatedValue = Constant.ZERO
        characterListViewModel.getCharactersList(paginatedValue)
        observeList()
    }

    private fun observeList() {
        lifecycle.coroutineScope.launchWhenCreated {
            characterListViewModel.marvelList.observe(viewLifecycleOwner) { it ->
                if (it.isLoading) {
                    binding.isLoading= true
                } else if (it.error.isNotBlank()) {
                    binding.isLoading= false
                    Toast.makeText(
                        this@CharacterListFragment.requireContext(),
                        it.error,
                        Toast.LENGTH_LONG
                    ).show()
                } else if (it.modelCharacterList.isNotEmpty()) {
                    binding.isLoading= false
                    binding.characterListViewModel = characterListViewModel
                }
            }
            delay(Constant.DELAY)
        }
    }

    override fun click(charId: Int) {
        val action =
            CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                charId
            )
        findNavController().navigate(action)
    }

}