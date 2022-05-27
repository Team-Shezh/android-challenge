package com.vectorinc.ezinwavictorandroidchallenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.vectorinc.ezinwavictorandroidchallenge.databinding.FragmentUserRepositoryListBinding
import com.vectorinc.ezinwavictorandroidchallenge.domain.adapter.Adapter
import com.vectorinc.ezinwavictorandroidchallenge.model.VerifyViewModel

/**
 * A SIMPLE FRAGMENT CLASS SHOWING REPOSITORY LIST OF USERS
 */

class RepositoryList : Fragment() {

    private val sharedViewModel: VerifyViewModel by activityViewModels()
    private var binding: FragmentUserRepositoryListBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentUserRepositoryListBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        //Set Layout manager of the Adapter
        binding?.recyclerView?.layoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)

        //Plugin a observable to the adapter list items
        sharedViewModel.repository.observe(
            viewLifecycleOwner
        ) { list ->
            //Plugin in adapter class to the recyclerView
            binding?.recyclerView?.adapter = Adapter(list)
        }


    }


}