package com.vectorinc.ezinwavictorandroidchallenge.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vectorinc.ezinwavictorandroidchallenge.network.ApiService
import com.vectorinc.ezinwavictorandroidchallenge.network.model.ListOfRepositoryDto
import com.vectorinc.ezinwavictorandroidchallenge.network.model.UsernameDto
import kotlinx.coroutines.launch

/**
 * A SharedViewModel data class
 */

class VerifyViewModel : ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name


    private val _results = MutableLiveData<UsernameDto>()
    val result: LiveData<UsernameDto> = _results

    private val _repository = MutableLiveData<List<ListOfRepositoryDto>>()
    val repository: LiveData<List<ListOfRepositoryDto>> = _repository

    private val _isUserExits = MutableLiveData<Boolean>()
    val isUserExits: LiveData<Boolean> = _isUserExits


    /*
    * Set Username Query to the ViewModel
    */
    fun setName(name: String) {
        _name.value = name
        getNameGithub(_name.value.toString())

    }


    /*
    * Creating a Call CoroutineScope to the API calls.
    */
    private fun getNameGithub(username: String) {
        viewModelScope.launch {
            try {
                //Call object to get list of Github Repos.
                _repository.value = ApiService.UsernameApi.retrofit.getRepositories(username)

                //Call object to get  Github Username.
                _results.value = ApiService.UsernameApi.retrofit.getUser(username)
                setToDefault(true)

            } catch (e: Exception) {
                setToDefault(false)

            }
        }
    }


    /*
    * Setting Boolean to know if user exits.
    */
    fun setToDefault(value: Boolean) {
        _isUserExits.value = value
    }


}