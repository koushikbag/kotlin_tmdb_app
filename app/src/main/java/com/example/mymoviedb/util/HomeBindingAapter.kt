package com.example.mymoviedb.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mymoviedb.R
import com.example.mymoviedb.model.Results
import com.example.mymoviedb.ui.adapter.MoviesAdapter

val IMAGE_BASE_URL_PATH = "https://image.tmdb.org/t/p/w185"
/**
 * Binding adapter used to hide the spinner once data is available
 */
@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.GONE else View.VISIBLE
}

@BindingAdapter("movieListData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Results>?) {
    val adapter = recyclerView.adapter as? MoviesAdapter
    adapter?.submitList(data)
    //adapter.updateList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        val completeUrl = IMAGE_BASE_URL_PATH + imageUrl
        Glide.with(imageView.context)
            .load(completeUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}