package com.vectorinc.ezinwavictorandroidchallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.vectorinc.ezinwavictorandroidchallenge.databinding.FragmentCheckUsernameBinding
import com.vectorinc.ezinwavictorandroidchallenge.model.VerifyViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [CheckUsernameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheckUsernameFragment : Fragment() {
    private var binding: FragmentCheckUsernameBinding? = null

    private val sharedViewModel : VerifyViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentCheckUsernameBinding.inflate(inflater,container,false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = sharedViewModel
            lifecycleOwner  = viewLifecycleOwner
            usernameFragment = this@CheckUsernameFragment
            usernameBtn.setOnClickListener {
                sharedViewModel.setName(usernameTxt.editText?.text.toString())
            }


        }
    }

    private fun moveToNextScreen(){
        findNavController().navigate(R.id.action_checkUsernameFragment_to_userRepositoryListFragment)
    }

}