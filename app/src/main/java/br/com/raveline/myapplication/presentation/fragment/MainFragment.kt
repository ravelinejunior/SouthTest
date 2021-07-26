package br.com.raveline.myapplication.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.raveline.myapplication.MainActivity
import br.com.raveline.myapplication.R
import br.com.raveline.myapplication.data.utils.Resource
import br.com.raveline.myapplication.databinding.FragmentMainBinding
import br.com.raveline.myapplication.presentation.adapter.EventsAdapter
import br.com.raveline.myapplication.presentation.viewmodel.EventViewModel

class MainFragment : Fragment() {
    private lateinit var mainBinding: FragmentMainBinding
    lateinit var eventViewModel: EventViewModel
    private lateinit var eventsAdapter: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainBinding = FragmentMainBinding.bind(view)
        eventViewModel = (activity as MainActivity).eventViewModel
        initRecyclerView()
        viewEventsList()
    }

    private fun viewEventsList() {
        eventViewModel.getEvents()
        //começar a observar os eventos
        eventViewModel.events.observe(viewLifecycleOwner, { response ->

            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { event ->
                        eventsAdapter.differ.submitList(event.toList())
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()

                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "Ocorreu um erro: $it", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun initRecyclerView() {
        eventsAdapter = EventsAdapter()
        mainBinding.recyclerViewMainFragment.apply {
            adapter = eventsAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity,2,RecyclerView.VERTICAL,false)

        }
    }

    private fun showProgressBar() {
        mainBinding.progressBarMainFragment.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        mainBinding.progressBarMainFragment.visibility = View.GONE
    }

}