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
import com.example.marvel.common.Constants
import com.example.marvel.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var characterListAdapter: CharacterListAdapter
    private lateinit var layoutManager: GridLayoutManager
    private val characterListViewModel: CharacterListViewModel by viewModels()
    private lateinit var binding: FragmentCharacterListBinding
    private var flag = 3
    private var paginatedValue = 0

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

        recyclerView = binding.rvCharacterList
        layoutManager = GridLayoutManager(this@CharacterListFragment.requireContext(), 3)
        recyclerViewProperties()

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 1) {
                    paginatedValue += 20
                    characterListViewModel.getCharactersList(paginatedValue)
                    observeList()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        paginatedValue = 0
        characterListViewModel.getCharactersList(paginatedValue)
        observeList()
    }

    private fun recyclerViewProperties() {
        characterListAdapter =
            CharacterListAdapter(this.requireContext(), mutableListOf(), findNavController())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = characterListAdapter

    }

    private fun observeList() {
        lifecycle.coroutineScope.launchWhenCreated {
            characterListViewModel.marvelList.observe(viewLifecycleOwner) { it ->
                if (it.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                } else if (it.error.isNotBlank()) {
                    binding.progressBar.visibility = View.GONE
                    flag = 0
                    Toast.makeText(
                        this@CharacterListFragment.requireContext(),
                        it.error,
                        Toast.LENGTH_LONG
                    ).show()
                } else if (it.modelCharacterList.isNotEmpty()) {
                    binding.progressBar.visibility = View.GONE
                    flag = 0
                    characterListAdapter.setContentList(it.modelCharacterList.toMutableList())
                }
            }
            delay(Constants.DELAY)
        }
    }

}