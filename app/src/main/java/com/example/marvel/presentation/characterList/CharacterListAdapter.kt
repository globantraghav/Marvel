package com.example.marvel.presentation.characterList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel.common.Constants
import com.example.marvel.databinding.ItemCharactersBinding
import com.example.marvel.domain.model.ModelCharacter

class CharacterListAdapter(
    private val context: Context,
    private var modelCharacterList: MutableList<ModelCharacter>,
    private var navController: NavController
) : RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    fun setContentList(list: MutableList<ModelCharacter>) {
        this.modelCharacterList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val mView =
            ItemCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(mView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.characterName.text = modelCharacterList[position].name
        val url = "${
            modelCharacterList[position].thumbnail.replace(
                Constants.HTTP,
                Constants.HTTPS
            )
        }/portrait_xlarge.${modelCharacterList[position].thumbnailExt}"
        Glide.with(context)
            .load(url)
            .into(holder.characterImage)

        holder.itemView.setOnClickListener {
            val action =
                CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                    modelCharacterList[position].id
                )
            navController.navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return modelCharacterList.size
    }

    class ViewHolder(binding: ItemCharactersBinding) : RecyclerView.ViewHolder(binding.root) {
        val characterName = binding.tvCharacterName
        val characterImage = binding.ivCharacter

    }

}