package br.com.raveline.myapplication.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.raveline.myapplication.R
import br.com.raveline.myapplication.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private lateinit var mainBinding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainBinding = FragmentMainBinding.bind(view)

        mainBinding.text.setOnClickListener {
            findNavController().navigate(
                R.id.action_mainFragment_to_detailsFragment
            )
        }
    }

}