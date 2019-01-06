package org.geepawhill.contentment.utility


import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertEquals

class NamesTest {

    @BeforeEach
    fun before() {
        Names.reset()
    }

    @Test
    fun sequences() {
        assertEquals("Test_1", Names.make("Test"))
        assertEquals("Test_2", Names.make("Test"))
    }

    @Test
    fun rootCounts() {
        assertEquals("Test_1", Names.make("Test"))
        assertEquals("Something_1", Names.make("Something"))
    }

    @Test
    fun className() {
        assertEquals("String_1", Names.make(String::class.java))
    }

    @Test
    fun reset() {
        assertEquals("String_1", Names.make(String::class.java))
        assertEquals("String_2", Names.make(String::class.java))
        Names.reset()
        assertEquals("String_1", Names.make(String::class.java))
    }

}
