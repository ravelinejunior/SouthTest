package br.com.raveline.myapplication.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.raveline.myapplication.data.model.EventModel
import br.com.raveline.myapplication.data.utils.Resource
import br.com.raveline.myapplication.domain.usecase.GetEventsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventViewModel(
    private val getEventsUseCase: GetEventsUseCase,
    private val application: Application
) : ViewModel() {
     val events: MutableLiveData<Resource<EventModel>> = MutableLiveData()

    fun getEvents() = viewModelScope.launch(Dispatchers.IO) {

        //indicar loading no initState da aplicação
        events.postValue(Resource.Loading())

        try {
            if (isNetworkAvailable(application)) {
                val apiResult = getEventsUseCase.execute()
                //após requisição ter sido feita, alterar estado da variavel
                events.postValue(apiResult)
            } else {
                events.postValue(Resource.Error("Verifique sua conexão com a internet e tente novamente!"))
            }
        }catch (e:Exception){
            events.postValue(Resource.Error("Erro: ${e.message}"))
        }

    }


    @Suppress("DEPRECATION")
    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }

                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }

        return false
    }

}