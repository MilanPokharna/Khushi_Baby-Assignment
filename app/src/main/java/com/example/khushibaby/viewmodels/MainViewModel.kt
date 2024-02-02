package com.example.khushibaby.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.example.khushibaby.models.MovieListResponse
import com.example.khushibaby.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class MainViewModel @Inject constructor(
    val mainRepository: MainRepository) : ViewModel() {

        val movieList = mainRepository.getMovieList().cachedIn(viewModelScope)

//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            mainRepository.getMovieList(1)
//        }
//    }
//
//    val movieListResponse : LiveData<MovieListResponse>
//        get() = mainRepository.movieListResponseLiveData




}