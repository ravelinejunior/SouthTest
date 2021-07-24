package br.com.raveline.myapplication.data.repository.datasource_impl

import br.com.raveline.myapplication.data.api.EventsApiService
import br.com.raveline.myapplication.data.model.EventItemModel
import br.com.raveline.myapplication.data.model.EventModel
import br.com.raveline.myapplication.data.model.PeopleModel
import br.com.raveline.myapplication.data.repository.datasource.EventRemoteDataSource
import retrofit2.Response

class EventsRemoteDataSourceImpl(
    private val eventsApiService: EventsApiService,
    private val id: Int
) : EventRemoteDataSource {
    override suspend fun getEvents(): Response<EventModel> {
        return eventsApiService.getEventsRequest()
    }

    override suspend fun getEventById(): Response<EventItemModel> {
        return eventsApiService.getEventById(id)
    }

    override suspend fun postCheckIn(): Response<PeopleModel> {
        return eventsApiService.postCheckInEvent()
    }
}