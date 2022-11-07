package com.edu.labs.albumstest.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edu.labs.albumstest.databinding.RowAlbumBinding
import com.edu.labs.albumstest.domain.model.Album

class AlbumsAdapter(
    private val onClickListener: (Album) -> Unit
) : RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {

    private var data: MutableList<Album> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowAlbumBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = data[position]
        holder.binding.also {
            it.album = album
            it.setOnClick { onClickListener(album) }
            it.executePendingBindings()
        }
    }

    fun updateData(list: List<Album>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        val binding: RowAlbumBinding
    ) : RecyclerView.ViewHolder(binding.root)

}