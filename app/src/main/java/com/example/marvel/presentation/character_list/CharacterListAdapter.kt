package com.example.marvel.presentation.character_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel.databinding.ItemCharactersBinding
import com.example.marvel.domain.model.Character

class CharacterListAdapter(private val context: Context,
                           private var characterList:MutableList<Character>,
                           private var navController: NavController):RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    fun setContentList(list:MutableList<Character>){
        this.characterList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val mView = ItemCharactersBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(mView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.characterName.text = characterList[position].name
        val url = "${characterList[position].thumbnail.replace("http","https")}/portrait_xlarge.${characterList[position].thumbnailExt}"
        Glide.with(context)
            .load(url)
            .into(holder.characterImage)

        holder.itemView.setOnClickListener {
            val action=  CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(characterList[position].id)
            navController.navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    class ViewHolder(binding: ItemCharactersBinding) : RecyclerView.ViewHolder(binding.root) {
        val characterName = binding.tvCharacterName
        val characterImage = binding.ivCharacter

    }

}