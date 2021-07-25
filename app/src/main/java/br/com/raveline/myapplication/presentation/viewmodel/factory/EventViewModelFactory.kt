package br.com.raveline.myapplication.presentation.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.raveline.myapplication.domain.usecase.GetEventsUseCase
import br.com.raveline.myapplication.presentation.viewmodel.EventViewModel

class EventViewModelFactory(
    private val getEventsUseCase: GetEventsUseCase,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EventViewModel(
            getEventsUseCase,
            application
        ) as T
    }
}