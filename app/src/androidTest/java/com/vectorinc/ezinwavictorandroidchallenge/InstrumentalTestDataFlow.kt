package com.vectorinc.ezinwavictorandroidchallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vectorinc.ezinwavictorandroidchallenge.model.VerifyViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing d@Test
fun useAppContext() {
 */
@RunWith(AndroidJUnit4::class)
class InstrumentalTestDataFlow {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val viewModel = VerifyViewModel()
        viewModel.name.observeForever {}

        mockWebServer = MockWebServer()


    }

    @Test
    fun `entree_menu_item_content`() {
        val viewModel = VerifyViewModel()
        viewModel.name.observeForever {}

        // launch the entree menu fragment
        launchFragmentInContainer<CheckUsernameFragment>(themeResId = R.style.Theme_EzinwaVictorAndroidChallenge)
        onView(withId(R.id.username_edit_text)).perform(typeText("Hector"))
            .perform(closeSoftKeyboard())

        onView(withId(R.id.username_btn)).perform(click())

        Assert.assertEquals("Hector", viewModel.result.value?.name)
    }


}