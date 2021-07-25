package br.com.raveline.myapplication.presentation.di

import br.com.raveline.myapplication.BuildConfig
import br.com.raveline.myapplication.data.api.EventsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }


    @Provides
    @Singleton
    fun provideEventsApiService(
        retrofit: Retrofit
    ):EventsApiService{
        return retrofit.create(EventsApiService::class.java)
    }
}