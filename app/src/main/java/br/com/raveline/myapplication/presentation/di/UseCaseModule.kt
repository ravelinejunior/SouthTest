package br.com.raveline.myapplication.presentation.di

import br.com.raveline.myapplication.domain.repository.EventsRepository
import br.com.raveline.myapplication.domain.usecase.CheckInUseCase
import br.com.raveline.myapplication.domain.usecase.GetEventsByIdUseCase
import br.com.raveline.myapplication.domain.usecase.GetEventsUseCase
import br.com.raveline.myapplication.domain.usecase.ShareEventUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetEventsUseCase(eventsRepository: EventsRepository):GetEventsUseCase{
        return GetEventsUseCase(eventsRepository)
    }

    @Provides
    @Singleton
    fun provideGetEventById(eventsRepository: EventsRepository):GetEventsByIdUseCase{
        return GetEventsByIdUseCase(eventsRepository)
    }

    @Provides
    @Singleton
    fun provideCheckInUseCase(eventsRepository: EventsRepository):CheckInUseCase{
        return CheckInUseCase(eventsRepository)
    }

    @Provides
    @Singleton
    fun provideShareUseCase(eventsRepository: EventsRepository):ShareEventUseCase{
        return ShareEventUseCase(eventsRepository)
    }

}