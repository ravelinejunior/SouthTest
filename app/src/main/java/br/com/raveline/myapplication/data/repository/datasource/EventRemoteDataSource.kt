package br.com.raveline.myapplication.data.repository.datasource

import br.com.raveline.myapplication.data.model.EventItemModel
import br.com.raveline.myapplication.data.model.EventModel
import br.com.raveline.myapplication.data.model.PeopleModel
import retrofit2.Response

interface EventRemoteDataSource {
    suspend fun getEvents(): Response<EventModel>
    suspend fun getEventById(id:Int): Response<EventItemModel>
    suspend fun postCheckIn(): Response<PeopleModel>
    fun shareEvent(): PeopleModel
}