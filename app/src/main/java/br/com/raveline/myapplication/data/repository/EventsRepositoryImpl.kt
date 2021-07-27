package br.com.raveline.myapplication.data.repository

import br.com.raveline.myapplication.data.model.EventItemModel
import br.com.raveline.myapplication.data.model.EventModel
import br.com.raveline.myapplication.data.model.PeopleModel
import br.com.raveline.myapplication.data.repository.datasource.EventRemoteDataSource
import br.com.raveline.myapplication.data.utils.Resource
import br.com.raveline.myapplication.domain.repository.EventsRepository
import retrofit2.Response

class EventsRepositoryImpl(
    private val eventsDataSource: EventRemoteDataSource
) : EventsRepository {
    override suspend fun getEvents(): Resource<EventModel> {
        return responseToResource(eventsDataSource.getEvents())
    }

    override suspend fun getEventsById(id: Int): Resource<EventItemModel> {
        return responseToResourceItem(eventsDataSource.getEventById(id))
    }

    override suspend fun shareEvent(peopleModel: PeopleModel): PeopleModel {
       return peopleModel
    }

    override suspend fun checkInEvent(peopleModel: PeopleModel): Resource<PeopleModel> {
        return responseToResourcePost(eventsDataSource.postCheckIn())
    }

    //um converter para recuperar o response e setar o resource como tipo pro viewModel
    private fun responseToResource(response: Response<EventModel>): Resource<EventModel> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    private fun responseToResourceItem(response: Response<EventItemModel>): Resource<EventItemModel> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    private fun responseToResourcePost(response: Response<PeopleModel>): Resource<PeopleModel> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

}