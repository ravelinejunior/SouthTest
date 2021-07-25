package br.com.raveline.myapplication.data.api

import br.com.raveline.myapplication.data.model.EventItemModel
import br.com.raveline.myapplication.data.model.EventModel
import br.com.raveline.myapplication.data.model.PeopleModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventsApiService {
    @GET("events")
    suspend fun getEventsRequest(): Response<EventModel>

    @GET("events/{id}")
    suspend fun getEventById(
        @Path("id") id: Int
    ):Response<EventItemModel>

    @POST("checkin")
    suspend fun postCheckInEvent(): Response<PeopleModel>
}