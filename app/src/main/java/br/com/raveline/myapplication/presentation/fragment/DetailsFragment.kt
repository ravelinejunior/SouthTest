package br.com.raveline.myapplication.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.raveline.myapplication.MainActivity
import br.com.raveline.myapplication.R
import br.com.raveline.myapplication.data.model.EventItemModel
import br.com.raveline.myapplication.data.model.PeopleModel
import br.com.raveline.myapplication.data.utils.Resource
import br.com.raveline.myapplication.databinding.FragmentDetailsBinding
import br.com.raveline.myapplication.presentation.adapter.EventsAdapter.Companion.noImageUrl
import br.com.raveline.myapplication.presentation.viewmodel.EventViewModel
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*


class DetailsFragment : Fragment() {

    private lateinit var detailsBinding: FragmentDetailsBinding
    private lateinit var eventViewModel: EventViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsBinding = FragmentDetailsBinding.bind(view)
        eventViewModel = (activity as MainActivity).eventViewModel

        val args: DetailsFragmentArgs by navArgs()
        val eventItem = args.eventItem

        detailsBinding.apply {

            if (verifyImageSource(eventItem.image!!)) {
                Glide.with(requireContext()).load(eventItem.image).into(ivDetail)
            } else {
                Glide.with(requireContext()).load(noImageUrl).into(ivDetail)
            }

            tvTitle.text = eventItem.title
            tvDesc.text = eventItem.description
            tvDate.text = "Publicado em ${convertLongToTime(eventItem.date!!.toLong())}"

            buttonSend.setOnClickListener {
                sendRequest(eventItem)
            }
        }

        detailsBinding.ivBack.setOnClickListener {
            findNavController().navigate(
                R.id.action_detailsFragment_to_mainFragment
            )
        }

    }

    private fun sendRequest(item: EventItemModel) {
        val peopleModel = PeopleModel(
            item.id,
            item.id,
            "Junior Raveline",
            item.image,
            "junior.raveline@hotmail.com",
        )
        eventViewModel.sendEvent(id, peopleModel)

        eventViewModel.sendEvents.observe(viewLifecycleOwner, { response ->

            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { event ->
                        Toast.makeText(activity, "Enviado com Sucesso!", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(
                            R.id.action_detailsFragment_to_mainFragment
                        )
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

    private fun share(){

    }


    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return format.format(date)
    }

    private fun verifyImageSource(url: String): Boolean {
        val urlRegex = Patterns.WEB_URL.matcher(url)
        return urlRegex.matches()
    }


    private fun showProgressBar() {
        detailsBinding.progressBarDetail.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        detailsBinding.progressBarDetail.visibility = View.GONE
    }


}