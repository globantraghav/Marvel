package com.example.marvel.presentation.characterList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.Constant
import com.example.marvel.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment(), CharacterClick {

    private lateinit var characterListAdapter: CharacterListAdapter
    private lateinit var layoutManager: GridLayoutManager
    private val characterListViewModel: CharacterListViewModel by viewModels()
    private lateinit var binding: FragmentCharacterListBinding
    private var paginatedValue = ZERO

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

        binding.characterListViewModel = characterListViewModel
        binding.lifecycleOwner = this

        characterListAdapter =
            CharacterListAdapter(mutableListOf(), this)
        binding.characterListAdapter = characterListAdapter
        layoutManager = GridLayoutManager(this@CharacterListFragment.requireContext(), 3)

        binding.rvCharacterList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 1) {
                    paginatedValue += Constant.paginatedValue
                    characterListViewModel.getCharactersList(paginatedValue)
                    observeError()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        paginatedValue = ZERO
        characterListViewModel.getCharactersList(paginatedValue)
        observeError()
    }

    private fun observeError() {
        characterListViewModel.errorMessage.observe(this) {
            if (it.isNotEmpty()) {
                Toast.makeText(
                    this@CharacterListFragment.requireContext(),
                    it,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun click(charId: Int) {
        val action =
            CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                charId
            )
        findNavController().navigate(action)
    }

    companion object {
        const val ZERO = 0
    }
}