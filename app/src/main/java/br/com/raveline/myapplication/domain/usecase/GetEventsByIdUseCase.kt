package br.com.raveline.myapplication.domain.usecase

import br.com.raveline.myapplication.data.model.EventItemModel
import br.com.raveline.myapplication.data.utils.Resource
import br.com.raveline.myapplication.domain.repository.EventsRepository

class GetEventsByIdUseCase(
    private val eventsRepository: EventsRepository
) {
    suspend fun execute(id:Int):Resource<EventItemModel> = eventsRepository.getEventsById(id)
}