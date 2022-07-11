package com.example.marvel.presentation.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.ModelCharacter
import com.example.marvel.presentation.characterDetails.CharacterDetailViewModel.Companion.HTTP
import com.example.marvel.presentation.characterDetails.CharacterDetailViewModel.Companion.HTTPS
import com.example.marvel.presentation.characterList.CharacterListAdapter

@BindingAdapter("setAdapter")
fun setAdapter(
    recyclerView: RecyclerView,
    adapter: CharacterListAdapter?
) {
    adapter?.let {
        recyclerView.adapter = it
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(
    recyclerView: RecyclerView, list: MutableList<ModelCharacter>?
) {
    val adapter = recyclerView.adapter as CharacterListAdapter
    adapter.setContentList(list ?: mutableListOf())
}

@BindingAdapter("visibility")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("url", "extension")
fun setImage(imageView: ImageView, url: String?, extension: String?) {

    val finalUrl = "${
        url?.replace(
            HTTP,
            HTTPS
        )
    }/portrait_xlarge.${extension}"

    Glide.with(imageView.context)
        .load(finalUrl)
        .into(imageView)
}