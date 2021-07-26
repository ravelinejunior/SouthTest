package br.com.raveline.myapplication.presentation.di

import br.com.raveline.myapplication.presentation.adapter.EventsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    @Singleton
    fun provideEventsAdapter(): EventsAdapter {
        return EventsAdapter()
    }


}