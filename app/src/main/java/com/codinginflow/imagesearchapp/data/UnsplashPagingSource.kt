package com.codinginflow.imagesearchapp.data

import androidx.paging.PagingSource
import com.codinginflow.imagesearchapp.api.UnsplashApi
import retrofit2.HttpException
import java.io.IOException

// Define a constant for the starting page index
private const val UNSPLASH_STARTING_PAGE_INDEX = 1

// Define a class that extends the PagingSource class with UnsplashPhoto as the type of the data
class UnsplashPagingSource(
    private val unsplashApi: UnsplashApi, // Inject UnsplashApi
    private val query: String // Search query string
) : PagingSource<Int, UnsplashPhoto>() {

    // Override the load() function to load a page of data
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        // Try to get the photos from the Unsplash API
        return try {
            val response = unsplashApi.searchPhotos(query, position, params.loadSize)
            val photos = response.results

            // Return a LoadResult object that contains the photos and page keys
            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            // If there is an IO error, return a LoadResult.Error object with the exception
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            // If there is an HTTP error, return a LoadResult.Error object with the exception
            LoadResult.Error(exception)
        }
    }
}
