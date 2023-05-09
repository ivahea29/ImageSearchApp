// Declare the package for this file
package com.codinginflow.imagesearchapp.di

// Import necessary libraries
import com.codinginflow.imagesearchapp.api.UnsplashApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Declare that this class is a Dagger module
@Module
// Declare which Dagger component this module will be installed in
@InstallIn(ApplicationComponent::class)
object AppModule {

    // Declare a function that provides a Retrofit instance
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(UnsplashApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // Declare a function that provides an instance of the Unsplash API interface
    @Provides
    @Singleton
    fun provideUnsplashApi(retrofit: Retrofit): UnsplashApi =
        retrofit.create(UnsplashApi::class.java)

}
