package com.vectorinc.ezinwavictorandroidchallenge

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.vectorinc.ezinwavictorandroidchallenge.databinding.FragmentCheckUsernameBinding
import com.vectorinc.ezinwavictorandroidchallenge.model.VerifyViewModel


/**
 * A simple [Fragment] to check the Github username
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
            lifecycleOwner = viewLifecycleOwner
            usernameFragment = this@CheckUsernameFragment
            usernameBtn.setOnClickListener {
                //Condition to check if user enter a blank or empty string
                if (usernameEditText.text.toString().isEmpty()) {
                    Toast.makeText(
                        activity?.applicationContext,
                        "Please Enter Username",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                //Show Progress Indication
                linearProgressIndicator.visibility = View.VISIBLE

                //Set name query to the sharedViewModel
                sharedViewModel.setName(usernameEditText.text.toString())
            }

        }

        //Plugin Observers to set Error if User doesn't exist
        sharedViewModel.isUserExits.observe(viewLifecycleOwner) {
            setErrorTextField(it)
        }

    }

    //Error Test Function
    fun setErrorTextField(error: Boolean) {
        if (!error) {
            binding?.usernameTxtLayout?.isErrorEnabled = true
            binding?.usernameTxtLayout?.error = getString(R.string.username_not_found)
            binding?.linearProgressIndicator?.visibility = View.INVISIBLE

        } else {
            moveToNextScreen()
            sharedViewModel.setToDefault(false)
            binding?.usernameTxtLayout?.isErrorEnabled = false
            binding?.usernameEditText?.text = null
            binding?.linearProgressIndicator?.visibility = View.INVISIBLE

        }
    }


    override fun onResume() {
        super.onResume()
        binding?.usernameTxtLayout?.isErrorEnabled = false
        binding?.usernameEditText?.text = null
        Log.d("Resume", "Called")
    }

    //Move to next Fragment
    private fun moveToNextScreen() {
        findNavController().navigate(R.id.action_checkUsernameFragment_to_userRepositoryListFragment)
    }

}