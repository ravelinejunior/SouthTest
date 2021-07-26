package br.com.raveline.myapplication.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.raveline.myapplication.R
import br.com.raveline.myapplication.databinding.FragmentDetailsBinding
import br.com.raveline.myapplication.presentation.fragment.MainFragment.Companion.ITEM_EVENT_KEY


class DetailsFragment : Fragment() {

    private lateinit var detailsBinding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsBinding = FragmentDetailsBinding.bind(view)

        val args:DetailsFragmentArgs by navArgs()
        val eventItem = args.eventItem

        detailsBinding.text2.text = eventItem.title

        detailsBinding.text2.setOnClickListener {
            findNavController().navigate(
                R.id.action_detailsFragment_to_mainFragment
            )
        }


    }


}