package com.example.mymoviedb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.mymoviedb.R
import com.example.mymoviedb.databinding.FragmentHomeBinding
import com.example.mymoviedb.ui.adapter.MoviesAdapter
import com.example.mymoviedb.viewmodels.MoviesViewModel

class HomeScreenFragment : Fragment() {

    /**
     * Lazily initialize our [OverviewViewModel].
     */
    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        binding.setLifecycleOwner(viewLifecycleOwner)
        binding.viewModel = viewModel

        binding.movieList.adapter = MoviesAdapter()

        return binding.root
    }
}