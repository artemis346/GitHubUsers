package com.github.users.repository_impl.details

import com.github.users.network.api.user.UserApi
import com.github.users.repository.userdetails.IUserDetailsRepository
import com.github.users.repository_impl.*
import com.github.users.repository_impl.userdetails.UserDetailsRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import java.io.IOException
import java.net.SocketTimeoutException

@ExperimentalCoroutinesApi
class UserDetailsRepositoryMock {

    @Mock
    lateinit var api: UserApi

    @get:Rule
    var rule = CoroutineTestRule()

    private lateinit var repository: IUserDetailsRepository

    @Before
    fun setUp() {
        api = mock()
        repository = UserDetailsRepository(api)
    }

    @Test
    fun `HAPPY CASE get positive details data`() = runBlocking {
        given(api.getUserDetails(any())).willAnswer { responseDetailsValid }
        val result = repository.getSelectedUserDetails("JackWharton")
        assertNotNull(result)
        assertEquals(result.first(), dtoValid)
    }

    @Test
    fun `INVALID CASE get invalid data in response`() = runBlocking {
        responseDetailsInvalid.forEachIndexed { index, response ->
            given(api.getUserDetails(any())).willAnswer { response }
            val result = repository.getSelectedUserDetails("JackWharton")
            assertNotNull(result)
            assertEquals(result.first(), dtoInvalid[index])
        }
    }

    @Test
    fun `INVALID CASE some data in reponse are empty`() = runBlocking {
        responseDetailsEmptyValues.forEachIndexed { index, response ->
            given(api.getUserDetails(any())).willAnswer { response }
            val result = repository.getSelectedUserDetails("JackWharton")
            assertNotNull(result)
            assertEquals(result.first(), dtoEmpty[index])
        }
    }

    @Test
    fun `INVALID CASE api emit error`() = runBlocking {
        listOf(IOException(), SocketTimeoutException()).forEach { ex ->
            given(api.getUserDetails(any())).willAnswer { throw ex }
            val result = repository.getSelectedUserDetails("JackWharton")
            result.catch { exception ->
                assert(exception.javaClass == ex.javaClass)
            }.collect()
        }
    }
}

