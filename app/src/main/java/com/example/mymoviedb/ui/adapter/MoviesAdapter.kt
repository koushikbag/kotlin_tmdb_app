package com.example.mymoviedb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoviedb.databinding.ItemMovieListBinding
import com.example.mymoviedb.model.Results

class MoviesAdapter :
    ListAdapter<Results, MoviesAdapter.MoviesViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            ItemMovieListBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val results = getItem(position)
        holder.bind(results)
    }

    fun updateList(items: List<Results>) {

    }

    class MoviesViewHolder(private var binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(results: Results) {
            binding.movieItem = results
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Results]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
