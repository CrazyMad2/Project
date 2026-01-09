package com.crazymaddoctor.urbanescape

import com.crazymaddoctor.urbanescape.data.mock.MockData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MockDataTest {

    @Test
    fun normalize_removes_accents_and_lowercases() {
        val result = MockData.normalize("ÁÉÍÓÚ Ñ Ç")
        assertEquals("aeiou n c", result)
    }

    @Test
    fun getCities_filters_by_query() {
        val cities = MockData.getCities("barce")
        assertTrue(cities.any { it.id == "bcn" })
    }
}
