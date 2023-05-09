package com.codinginflow.imagesearchapp.api

import com.codinginflow.imagesearchapp.data.UnsplashPhoto

// Represents the response returned by the Unsplash API when requesting photos
data class UnsplashResponse(
    val results: List<UnsplashPhoto> // The list of photos returned by the API
)
