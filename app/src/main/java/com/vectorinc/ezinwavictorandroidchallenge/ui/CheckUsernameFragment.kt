package com.vectorinc.ezinwavictorandroidchallenge.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.vectorinc.ezinwavictorandroidchallenge.R
import com.vectorinc.ezinwavictorandroidchallenge.databinding.FragmentCheckUsernameBinding
import com.vectorinc.ezinwavictorandroidchallenge.model.VerifyViewModel


/**
 * A simple [Fragment] to check the Github username
 */
class CheckUsernameFragment : Fragment() {
    private var binding: FragmentCheckUsernameBinding? = null

    private val sharedViewModel: VerifyViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentCheckUsernameBinding.inflate(inflater, container, false)
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
                //Condition to check if user enter a blank or empty string and internet connection
                if (!checkForInternet(view.context)) {
                    Toast.makeText(
                        activity?.applicationContext,
                        getString(R.string.no_internet),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                if (usernameEditText.text.toString().isEmpty()) {
                    Toast.makeText(
                        activity?.applicationContext,
                        getString(R.string.username),
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
    }

    //Move to next Fragment
    private fun moveToNextScreen() {
        findNavController().navigate(R.id.action_checkUsernameFragment_to_userRepositoryListFragment)
    }


    //Check for Internet
    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

}