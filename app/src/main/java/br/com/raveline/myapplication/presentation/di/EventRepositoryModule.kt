package br.com.raveline.myapplication.presentation.di

import br.com.raveline.myapplication.data.repository.EventsRepositoryImpl
import br.com.raveline.myapplication.data.repository.datasource.EventRemoteDataSource
import br.com.raveline.myapplication.data.repository.datasource_impl.EventsRemoteDataSourceImpl
import br.com.raveline.myapplication.domain.repository.EventsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EventRepositoryModule {

    @Provides
    @Singleton
    fun provideEventsRepository(remoteDataSource: EventRemoteDataSource):EventsRepository{
        return EventsRepositoryImpl(remoteDataSource)
    }


}