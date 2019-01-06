package org.geepawhill.contentment.flow

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FormatTableTest {
    private val table = FormatTable()

    @Test
    fun findsAllNineValues() {
        for (size in Size.values()) {
            if (size == Size.None) continue
            for (color in Color.values()) {
                if (color == Color.None) continue
                assertThat(table[size, color]).isNotNull()
            }
        }
    }

    @Test
    fun missingSize() {
        try {
            table[Size.None, Color.Emphatic]
            Assertions.fail<Any>("No throw on missing size.")
        } catch (e: FormatTable.EntryNotFoundException) {
            assertThat(e.message).endsWith("Size not found.")
        }

        try {
            table[Size.Jumbo, Color.None]
            Assertions.fail<Any>("No throw on missing color.")
        } catch (e: FormatTable.EntryNotFoundException) {
            assertThat(e.message).endsWith("Color not found.")
        }

    }
}
