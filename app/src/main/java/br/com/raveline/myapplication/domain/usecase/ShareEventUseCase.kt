package br.com.raveline.myapplication.domain.usecase

import br.com.raveline.myapplication.data.model.PeopleModel
import br.com.raveline.myapplication.data.utils.Resource
import br.com.raveline.myapplication.domain.repository.EventsRepository

class ShareEventUseCase(
    private val eventsRepository: EventsRepository
) {
    suspend fun execute(peopleModel: PeopleModel):PeopleModel =
        eventsRepository.shareEvent(peopleModel)
}