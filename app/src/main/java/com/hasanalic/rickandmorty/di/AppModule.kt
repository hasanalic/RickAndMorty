package com.hasanalic.rickandmorty.di

import com.hasanalic.rickandmorty.api.RetrofitAPI
import com.hasanalic.rickandmorty.repo.DetailRepository
import com.hasanalic.rickandmorty.repo.DetailRepositoryImp
import com.hasanalic.rickandmorty.repo.ListRepository
import com.hasanalic.rickandmorty.repo.ListRepositoryImp
import com.hasanalic.rickandmorty.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRetrofitAPI(): RetrofitAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideListRepository(retrofitAPI: RetrofitAPI): ListRepository {
        return ListRepositoryImp(retrofitAPI)
    }

    @Singleton
    @Provides
    fun provideDetailRepository(retrofitAPI: RetrofitAPI): DetailRepository {
        return DetailRepositoryImp(retrofitAPI)
    }
}