package com.vectorinc.ezinwavictorandroidchallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vectorinc.ezinwavictorandroidchallenge.model.VerifyViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * local unit test of the ViewModel, which will execute on the development machine (host).
 *
 *
 */
class ViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Test
    fun checkUsernameVar() {
        val viewModel = VerifyViewModel()
        viewModel.name.observeForever {}
        viewModel.setName("Hector")
        assertEquals("Hector", viewModel.name.value)
    }

}