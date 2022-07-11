package com.example.marvel.presentation.characterList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ModelCharacter
import com.example.marvel.R
import com.example.marvel.databinding.ItemCharactersBinding

class CharacterListAdapter(
    private var modelCharacterList: MutableList<ModelCharacter>,
    private var characterClick: CharacterClick
) : RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    fun setContentList(list: MutableList<ModelCharacter>) {
        this.modelCharacterList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding: ItemCharactersBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_characters, parent, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(modelCharacterList[position], characterClick)
    }

    override fun getItemCount(): Int {
        return modelCharacterList.size
    }

    class ViewHolder(var binding: ItemCharactersBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: ModelCharacter, characterClick: CharacterClick) {
            binding.setVariable(BR.character, character)
            binding.setVariable(BR.characterClick, characterClick)
            binding.executePendingBindings()
        }
    }

}