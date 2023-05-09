package com.codinginflow.imagesearchapp.ui.gallery

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.codinginflow.imagesearchapp.data.UnsplashRepository

/**
 * ViewModel for the GalleryFragment.
 * @param repository the UnsplashRepository that provides access to the Unsplash API.
 * @param state the SavedStateHandle used to save the current search query.
 */
class GalleryViewModel @ViewModelInject constructor(
    private val repository: UnsplashRepository,
    @Assisted state: SavedStateHandle
) : ViewModel() {

    // Get the current search query from the SavedStateHandle.
    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    // Use switchMap to update the photos whenever the current search query changes.
    val photos = currentQuery.switchMap { queryString ->
        repository.getSearchResults(queryString).cachedIn(viewModelScope)
    }

    // Update the current search query.
    fun searchPhotos(query: String) {
        currentQuery.value = query
    }

    // Companion object that stores constants.
    companion object {
        private const val CURRENT_QUERY = "current_query"
        private const val DEFAULT_QUERY = "cats"
    }
}
