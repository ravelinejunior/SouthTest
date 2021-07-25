package br.com.raveline.myapplication.domain.repository

import br.com.raveline.myapplication.data.model.EventItemModel
import br.com.raveline.myapplication.data.model.EventModel
import br.com.raveline.myapplication.data.model.PeopleModel
import br.com.raveline.myapplication.data.utils.Resource

interface EventsRepository {
    suspend fun getEvents(): Resource<EventModel>
    suspend fun getEventsById(id:Int): Resource<EventItemModel>
    suspend fun shareEvent(peopleModel: PeopleModel)
    suspend fun checkInEvent(peopleModel: PeopleModel):Resource<PeopleModel>
}