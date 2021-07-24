package br.com.raveline.myapplication.domain.usecase

import br.com.raveline.myapplication.data.model.EventModel
import br.com.raveline.myapplication.data.utils.Resource
import br.com.raveline.myapplication.domain.repository.EventsRepository

class GetEventsUseCase(
    private val eventsRepository: EventsRepository
) {

    suspend fun execute():Resource<EventModel> = eventsRepository.getEvents()

}