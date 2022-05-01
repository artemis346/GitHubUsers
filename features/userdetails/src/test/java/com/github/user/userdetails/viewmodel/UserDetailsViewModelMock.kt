package com.github.user.userdetails.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.users.repository.userdetails.IUserDetailsRepository
import com.github.users.repository_impl.details.CoroutineTestRule
import com.github.users.userdetails.viewmodel.ErrorState
import com.github.users.userdetails.viewmodel.UserDetailsUiState
import com.github.users.userdetails.viewmodel.UserDetailsViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import java.io.IOException

class UserDetailsViewModelMock {

    @Mock
    lateinit var repository: IUserDetailsRepository

    lateinit var viewmodel: UserDetailsViewModel

    @get:Rule
    var rule = CoroutineTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = mock()
        viewmodel = UserDetailsViewModel(repository = repository)
    }

    @Test
    fun `HAPPY CASE retrieve valid date`() = runBlocking {
        given(repository.getSelectedUserDetails(any())).willAnswer { flowOf(dtoValid) }
        viewmodel.fetchUserDetails("JackWharton")
        val value = viewmodel.uiState.value
        assert(value is UserDetailsUiState.Success)
        val result = (value as UserDetailsUiState.Success).item
        assertEquals(result, itemValid)
    }

    @Test
    fun `ERROR CASE retrieve exception`() = runBlocking {
        given(repository.getSelectedUserDetails(any())).willAnswer { flowOf {throw IOException()} }
        viewmodel.fetchUserDetails("JackWharton")
        val value = viewmodel.uiState.value
        assert(value is UserDetailsUiState.Error)
        val result = (value as UserDetailsUiState.Error).error
        assert(result == ErrorState.ERROR_LOADING)
    }
}