package br.com.raveline.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.raveline.myapplication.databinding.ActivityMainBinding
import br.com.raveline.myapplication.presentation.adapter.EventsAdapter
import br.com.raveline.myapplication.presentation.viewmodel.EventViewModel
import br.com.raveline.myapplication.presentation.viewmodel.factory.EventViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var eventViewModel: EventViewModel

    @Inject
    lateinit var eventViewModelFactory: EventViewModelFactory

    @Inject
    lateinit var eventsAdapter: EventsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        eventViewModel =
            ViewModelProvider(this, eventViewModelFactory).get(EventViewModel::class.java)


    }
}