package com.example.mymoviedb.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.mymoviedb.model.Results
import com.example.mymoviedb.network.TmdbAPI
import kotlinx.coroutines.*
import timber.log.Timber

private const val API_KEY = "ac536e8df3a2d6bcdde3168f130594fc"

enum class MoviesAPIStatus { LOADING, ERROR, DONE }

class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<MoviesAPIStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<MoviesAPIStatus>
        get() = _status

    private val _popularMovies = MutableLiveData<List<Results>>()

    val popularMovies: LiveData<List<Results>>
        get() = _popularMovies

    val items:LiveData<String> = Transformations.map(_popularMovies) {
        it?.get(0)?.vote_count
    }

    init {
        Timber.i("Started")
        coroutineScope.launch {
            getPopularVideos()
        }
    }

    private suspend fun getPopularVideos(){
        withContext(Dispatchers.Main){
            Timber.i("OnScope")
            val moviesViewModelDeferred = TmdbAPI.tmdbAPIService.getPopularMovieList(
                API_KEY, "en-US", 1
            )
            try {
                _status.value = MoviesAPIStatus.LOADING
                val result = moviesViewModelDeferred.await()
                _status.value = MoviesAPIStatus.DONE
                Timber.i(result.toString())
                _popularMovies.value = result.results
            } catch (exception: Exception) {
                Timber.i(exception)
                _status.value = MoviesAPIStatus.ERROR
                _popularMovies.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}