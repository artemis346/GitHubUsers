package com.github.users.repository_impl.search.mappers

import com.github.users.repository_impl.*
import com.github.users.repository_impl.userdetails.mappers.mapToDomain
import junit.framework.Assert.assertEquals
import org.junit.Test

class UserDetailsMapperMock {

    @Test
    fun `invoke mapper when convert api to domain POSITIVE CASE`() {
        val response = responseDetailsValid.mapToDomain()
        assertEquals(response, dtoValid)
    }

    @Test
    fun `invoke mapper when convert api to domain INVALID `() {
        val response = responseDetailsInvalid.map {
            it.mapToDomain()
        }
        assertEquals(response, dtoInvalid)
    }

    @Test
    fun `invoke mapper when convert api to domain EMPTY FIELDS`() {
        val response = responseDetailsEmptyValues.map {
            it.mapToDomain()
        }
        assertEquals(response, dtoEmpty)
    }
}