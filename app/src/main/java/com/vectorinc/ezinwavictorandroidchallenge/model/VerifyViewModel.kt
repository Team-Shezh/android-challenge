package com.vectorinc.ezinwavictorandroidchallenge.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vectorinc.ezinwavictorandroidchallenge.network.ApiService
import com.vectorinc.ezinwavictorandroidchallenge.network.ApiService.UsernameApi
import kotlinx.coroutines.launch

/**
 * SharedView Model for all fragments [VerifyViewModel]
 */

class VerifyViewModel : ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name




    private fun getNameGithub() {
        viewModelScope.launch {
            try {
                val result = ApiService.UsernameApi.retrofit.getUser("Somtorizm")
                _name.value = "Success: ${result.name} Name retrieved"
                Log.d("Res",_name.value.toString())
            } catch (e: Exception) {
                _name.value = "Failure: ${e.message}"
            }
        }
    }
}