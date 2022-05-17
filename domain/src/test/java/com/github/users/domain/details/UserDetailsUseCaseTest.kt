package com.github.users.domain.details

import com.github.users.domain.CoroutineTestRule
import com.github.users.repository.userdetails.IUserDetailsRepository
import com.github.users.repository_impl.*
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
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
import java.net.SocketTimeoutException

@ExperimentalCoroutinesApi
class UserDetailsUseCaseTest {

    @Mock
    lateinit var repository: IUserDetailsRepository

    @get:Rule
    var rule = CoroutineTestRule()

    private lateinit var usecase: GetUserDetailsUseCase

    @Before
    fun setUp() {
        repository = mock()
        usecase = GetUserDetailsUseCaseImpl(repository)
    }

    @Test
    fun `HAPPY CASE get positive details data`() = runBlocking {
        given(repository.getSelectedUserDetails(any())).willAnswer { flowOf(dtoValid) }
        val result = repository.getSelectedUserDetails("JackWharton")
        assertNotNull(result)
        assertEquals(result.first(), dtoValid)
    }

    @Test
    fun `INVALID CASE get invalid data in response`() = runBlocking {
        dtoInvalid.forEachIndexed { index, domainObj ->
            given(repository.getSelectedUserDetails(any())).willAnswer { flowOf(domainObj) }
            val result = repository.getSelectedUserDetails("JackWharton")
            assertNotNull(result)
            assertEquals(result.first(), dtoInvalid[index])
        }
    }

    @Test
    fun `INVALID CASE some data in reponse are empty`() = runBlocking {
        dtoEmpty.forEachIndexed { index, domainObj ->
            given(repository.getSelectedUserDetails(any())).willAnswer { flowOf(domainObj) }
            val result = repository.getSelectedUserDetails("JackWharton")
            assertNotNull(result)
            assertEquals(result.first(), dtoEmpty[index])
        }
    }

    @Test
    fun `INVALID CASE api emit error`() = runBlocking {
        listOf(IOException(), SocketTimeoutException()).forEach { ex ->
            given(repository.getSelectedUserDetails(any())).willAnswer { flowOf { error(ex) } }
            val result = repository.getSelectedUserDetails("JackWharton")
            result.catch { exception ->
                assert(exception.javaClass == ex.javaClass)
            }.collect()
        }
    }
}

