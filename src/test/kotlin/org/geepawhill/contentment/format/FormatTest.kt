package org.geepawhill.contentment.format


import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.contentment.style.Frames
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class FormatTest {

    private val base = Format("Base")
    private val style = Frames.unspecified()
    private val key = style.key()

    @Test
    fun throwsOnMissingStyle() {

        Assertions.assertThrows(MissingFormatException::class.java
        ) { base.require(key) }
    }

    @Test
    fun overrides() {
        assertThat(base.find(key)).isNull()
        base.put(style)
        assertThat(style).isEqualTo(base.find(Frames.KEY))
    }

    @Test
    fun hasBase() {
        val derived = Format("Derived", base)
        assertThat(derived.base).isEqualTo(base)
    }

    @Test
    fun findsInBase() {
        base.put(style)
        val derived = Format("Derived", base)
        assertThat(derived.find(key)).isEqualTo(style)
    }
}
