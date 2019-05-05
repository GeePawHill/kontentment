package org.geepawhill.contentment.core

import javafx.application.Platform
import javafx.beans.binding.Bindings
import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Orientation
import javafx.scene.Cursor
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.ToolBar
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.Pane
import javafx.scene.media.MediaView
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.stage.Stage
import org.geepawhill.contentment.geometry.PointPair
import org.geepawhill.contentment.jfx.ScaleListener
import org.geepawhill.contentment.player.Player
import org.geepawhill.contentment.player.PlayerState
import org.geepawhill.contentment.rhythm.Rhythm
import org.geepawhill.contentment.utility.JfxUtility
import tornadofx.*
import java.util.concurrent.Callable

class MainView(private val stage: Stage, private val player: Player) : View() {

    private val isPlayingCallable = Callable { player.state == PlayerState.Playing }
    private val trueIfPlaying = Bindings.createBooleanBinding(isPlayingCallable, player.stateProperty())
    private lateinit var timing: Text

    override val root = borderpane {
        top = toolbar {
            orientation = Orientation.HORIZONTAL
            timing = makeTiming()
            button("Full") {
                action { stage.isFullScreen = true }
            }
            button("||<--") {
                action { player.start() }
                disableWhen { trueIfPlaying }
            }
            button("OneOff") {
                action { oneOff() }
            }
            button("<--") {
                action { player.backward() }
                disableWhen { trueIfPlaying }
            }
            button(">") {
                action { player.play() }
                disableWhen { trueIfPlaying }
            }
            button(">|") {
                action { player.playOne() }
                disableWhen { trueIfPlaying }
            }
            button("-->") {
                action { player.forward() }
                disableWhen { trueIfPlaying }
            }
            button("-->||") {
                action { player.end() }
                disableWhen { trueIfPlaying }
            }
            button("T-2") {
                action { player.penultimate() }
                disableWhen { trueIfPlaying }
            }
            button("T-1") {
                action { player.ultimate() }
                disableWhen { trueIfPlaying }
            }
            button("Mark") {
                action { markHere(this@toolbar) }
                enableWhen { trueIfPlaying }
            }
            button("Present!") {
                disableWhen { trueIfPlaying }
            }
        }
    }

    private fun ToolBar.makeTiming(): Text {
        return text("00000000") {
            font = Font("Consolas", 30.0)
            stroke = Color.BLUE
            fill = Color.BLUE
        }
    }

    private var media: MediaView? = null

    val node: Parent
        get() {
            root.center = makeViewport()
            return root
        }

    init {
        stage.fullScreenProperty().addListener { _, _, n -> fullscreenChanged(n!!) }
    }

    private fun makeViewport(): Pane {
        val owner = Pane()
        owner.setPrefSize(1600.0, 900.0)
        val background = Background(BackgroundFill(Color.BLACK, null, null))
        owner.background = background

        player.scriptProperty().addListener { _, _, _ -> scriptChanged() }

        media = MediaView()
        owner.children.add(media)

        // non-media background

        val listener = ScaleListener(owner, player.context().canvas, media as MediaView)
        owner.widthProperty().addListener(listener)
        owner.heightProperty().addListener(listener)
        listener.changed(SimpleObjectProperty(0.0), 300, 300)

        owner.setOnMouseClicked { event -> mouseClicked(event) }

        owner.children.add(player.context().canvas)
        return owner
    }

    private fun scriptChanged() {
        player.rhythm.beatProperty().addListener { _, _, n -> beatChanged(n) }
        beatChanged(0)
        media!!.mediaPlayer = player.script.mediaPlayer
    }

    private fun mouseClicked(event: MouseEvent) {
        if (event.isShiftDown && event.button == MouseButton.PRIMARY) {
            player.forward()
            return
        }
        if (event.button == MouseButton.SECONDARY)
            player.backward()
        else
            player.playOne()
    }

    private fun oneOff() {
        JfxUtility.capture(stage.scene.root)
        dumpNode(player.context().canvas, 0)
    }

    private fun dumpNode(node: Node, indent: Int) {
        var tabs = ""
        for (i in 0 until indent)
            tabs += "\t"
        print(tabs + node.javaClass.simpleName)
        print(" " + PointPair(node.boundsInParent))
        println()
        if (node is Parent) {
            for (child in node.childrenUnmodifiable) {
                dumpNode(child, indent + 1)
            }
        }
    }

    private fun beatChanged(beat: Number) {
        var text = String.format("%8d", beat.toLong() / 1000)
        if (beat.toLong() == 0L) text = "   Start"
        if (beat.toLong() == Rhythm.MAX) text = "     End"
        val newText = text
        Platform.runLater { timing.text = newText }
    }

    private fun markHere(bar: ToolBar) {
        val text = Text(String.format("%8d", player.rhythm.beat() / 1000))
        text.font = Font("Consolas", 30.0)
        text.stroke = Color.BLUE
        text.fill = Color.BLUE
        bar.items.add(text)
    }

    private fun fullscreenChanged(newValue: Boolean) {
        if (!newValue) {
            root.top.show()
            stage.scene.cursor = Cursor.DEFAULT
        } else {
            root.top.hide()
            stage.scene.cursor = Cursor.NONE
        }
    }


}
