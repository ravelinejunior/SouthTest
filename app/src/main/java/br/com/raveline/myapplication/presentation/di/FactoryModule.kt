package br.com.raveline.myapplication.presentation.di

import android.app.Application
import br.com.raveline.myapplication.domain.usecase.CheckInUseCase
import br.com.raveline.myapplication.domain.usecase.GetEventsUseCase
import br.com.raveline.myapplication.domain.usecase.ShareEventUseCase
import br.com.raveline.myapplication.presentation.viewmodel.factory.EventViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Provides
    @Singleton
    fun provideEventsViewModelFactory(
        getEventsUseCase: GetEventsUseCase,
        shareEventUseCase: ShareEventUseCase,
        checkInUseCase: CheckInUseCase,
        application: Application
    ): EventViewModelFactory {
        return EventViewModelFactory(
            getEventsUseCase,
            shareEventUseCase,
            checkInUseCase,
            application
        )
    }

}