package br.com.raveline.myapplication.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.raveline.myapplication.R
import br.com.raveline.myapplication.databinding.FragmentDetailsBinding


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

        detailsBinding.text2.setOnClickListener {
            findNavController().navigate(
                R.id.action_detailsFragment_to_mainFragment
            )
        }


    }


}