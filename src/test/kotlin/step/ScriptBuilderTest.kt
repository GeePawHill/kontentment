package step

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Fail
import org.geepawhill.contentment.core.Context
import org.geepawhill.contentment.core.Gesture
import org.geepawhill.contentment.core.OnFinished
import org.junit.jupiter.api.Test
import step.Phrase
import step.ScriptBuilder
import step.SlowPlayer
import java.util.*

class ScriptBuilderTest {

    internal var builder = TestScriptBuilder()

    internal class TestPlayer : SlowPlayer {

        override fun play(context: Context, onFinished: OnFinished, gestures: ArrayList<Gesture>) {}
    }

    internal class ExposedPhrase : Phrase(TestPlayer()) {

        fun gestures(): ArrayList<Gesture> {
            return gestures
        }
    }

    internal class TestScriptBuilder : ScriptBuilder<TestScriptBuilder>() {
        override fun downcast(): TestScriptBuilder {
            return this
        }

        override fun makePhrase(): Phrase {
            return ExposedPhrase()
        }

        fun lastStall(): Long {
            return lastStall
        }

        fun lastScene(): Long {
            return lastScene
        }

    }

    @Test
    fun endWithoutSceneThrows() {
        try {
            builder.end()
            Fail.fail<Any>("Didn't throw!")
        } catch (expected: Exception) {
        }

    }

    @Test
    fun sceneAddsScene() {
        builder.scene(0)
        assertThat(builder.lastScene()).isEqualTo(0)
        assertThat(builder.lastStall()).isEqualTo(0)
        builder.end()
        assertThat(builder.script.size()).isEqualTo(1)
        val phrase = builder.script[0].phrase as ExposedPhrase
        assertThat(phrase.gestures().size).isEqualTo(1)
    }

    @Test
    fun scenesStartAnywhere() {
        builder.scene(20)
        assertThat(builder.lastScene()).isEqualTo(20)
        assertThat(builder.lastStall()).isEqualTo(20)
        builder.end()
        assertThat(builder.script.size()).isEqualTo(1)
        val phrase = builder.script[0].phrase as ExposedPhrase
        assertThat(phrase.gestures().size).isEqualTo(1)
    }

    @Test
    fun normalFunctionsWork() {
        builder.scene(0)
        builder.wipe()
        builder.end()
        assertThat(builder.script.size()).isEqualTo(1)
        val phrase = builder.script[0].phrase as ExposedPhrase
        assertThat(phrase.gestures().size).isEqualTo(2)
    }

    @Test
    fun scenesChain() {
        builder.scene(0)
        builder.scene(5)
        assertThat(builder.lastScene()).isEqualTo(5)
        builder.end()
        assertThat(builder.script.size()).isEqualTo(2)
    }

    @Test
    fun stallsChain() {
        builder.scene(0)
        builder.sync(5)
        builder.sync(5)
        assertThat(builder.lastStall()).isEqualTo(10)
        builder.end()
        assertThat(builder.script.size()).isEqualTo(1)
    }

}
