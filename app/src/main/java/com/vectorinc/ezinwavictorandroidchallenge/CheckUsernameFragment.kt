package com.vectorinc.ezinwavictorandroidchallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.vectorinc.ezinwavictorandroidchallenge.model.VerifyViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CheckUsernameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheckUsernameFragment : Fragment() {
    private val sharedViewModel : VerifyViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_username, container, false)
    }

    private fun moveToNextScreen(){
        findNavController().navigate(R.id.action_checkUsernameFragment_to_userRepositoryListFragment)
    }

}