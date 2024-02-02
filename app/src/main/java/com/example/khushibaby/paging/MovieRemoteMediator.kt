package com.example.khushibaby.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.khushibaby.api.RetrofitService
import com.example.khushibaby.db.MovieDatabase
import com.example.khushibaby.models.Movie
import com.example.khushibaby.models.MovieRemoteKeys
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val movieApi: RetrofitService,
    private val movieDatabase: MovieDatabase
):RemoteMediator<Int,Movie>() {
    val movieDao = movieDatabase.moviesDao()
    val remoteKeyDao = movieDatabase.remoteKeyDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Movie>): MediatorResult {
        return try {
            val currentPage = when (loadType){
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1 )?: 1
                }
                LoadType.PREPEND -> {
//                    return MediatorResult.Success(endOfPaginationReached = true)
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage?:
                    return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage?:
                    return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }
            val response = movieApi.getMovieList(currentPage)
            val endOfPaginationReached = response.body()?.total_pages == currentPage

            val prevPage = if(currentPage == 1) null else currentPage-1
            val nextPage = if(endOfPaginationReached) null else currentPage+1

            movieDatabase.withTransaction {
                if(loadType == LoadType.REFRESH){
                    movieDao.deleteAllMovies()
                    remoteKeyDao.deleteRemoteKeys()
                }
                movieDao.addMovies(response.body()?.results!!)
                val keys = response.body()?.results!!.map {movie ->
                    MovieRemoteKeys(
                        movieId = movie.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                remoteKeyDao.addAllRemoteKeys(keys)
            }
            MediatorResult.Success(endOfPaginationReached)

        }catch (e: Exception){
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Movie>): MovieRemoteKeys? {
        return state.anchorPosition?.let {
            position -> state.closestItemToPosition(position)?.id?.let {id->
                remoteKeyDao.getRemoteKeys(id = id)
        }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Movie>): MovieRemoteKeys? {
        return state.pages.firstOrNull{it.data.isNotEmpty()}?.data?.firstOrNull()?.let {
            movie ->
            remoteKeyDao.getRemoteKeys(movie.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state:PagingState<Int, Movie>
    ): MovieRemoteKeys? {
        return state.pages.lastOrNull{it.data.isNotEmpty()}?.data?.lastOrNull()
            ?.let {movie ->
                remoteKeyDao.getRemoteKeys(movie.id)
            }
    }
}