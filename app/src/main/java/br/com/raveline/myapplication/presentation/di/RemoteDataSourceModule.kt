package br.com.raveline.myapplication.presentation.di

import br.com.raveline.myapplication.data.api.EventsApiService
import br.com.raveline.myapplication.data.repository.datasource.EventRemoteDataSource
import br.com.raveline.myapplication.data.repository.datasource_impl.EventsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideEventsRemoteDataSource(
        eventsApiService: EventsApiService
    ): EventRemoteDataSource {
        return EventsRemoteDataSourceImpl(eventsApiService)
    }


}