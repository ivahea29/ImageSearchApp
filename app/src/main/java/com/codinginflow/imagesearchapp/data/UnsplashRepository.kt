package com.codinginflow.imagesearchapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.codinginflow.imagesearchapp.api.UnsplashApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepository @Inject constructor(private val unsplashApi: UnsplashApi) {

    /**
     * Returns a LiveData object that emits a stream of PagingData objects containing
     * search results for the given query.
     *
     * @param query the search query string
     * @return a LiveData object that emits a stream of PagingData objects
     */
    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20, // number of items to load in a single page
                maxSize = 100, // maximum number of items to keep in memory
                enablePlaceholders = false // whether to show null placeholders for unloaded items
            ),
            // creates a new instance of UnsplashPagingSource for each search query
            pagingSourceFactory = { UnsplashPagingSource(unsplashApi, query) }
        ).liveData // converts the Pager object to a LiveData object
}
