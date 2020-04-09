package org.geepawhill.contentment.fragments

import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.contentment.core.Context
import org.geepawhill.contentment.rhythm.Rhythm
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SyncTest {
    private val rhythm = Rhythm()
    private val context = Context()
    private val sync = Sync(1)

    @BeforeEach
    fun before() {
        context.setRhythm(rhythm)
    }

    @Test
    fun continuesIfNotThereYet() {
        assertThat(sync.interpolate(context, 0.0)).isTrue()
    }

    @Test
    fun finishesIfThere() {
        rhythm.seek(2000)
        assertThat(sync.interpolate(context, 0.0)).isFalse()
    }

}
