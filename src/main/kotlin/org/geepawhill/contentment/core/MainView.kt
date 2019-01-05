package org.geepawhill.contentment.core

import org.geepawhill.contentment.geometry.PointPair
import org.geepawhill.contentment.jfx.ScaleListener
import org.geepawhill.contentment.player.Player
import org.geepawhill.contentment.player.PlayerState
import org.geepawhill.contentment.rhythm.Rhythm
import org.geepawhill.contentment.utility.JfxUtility

import javafx.application.Platform
import javafx.beans.binding.Bindings
import javafx.beans.binding.BooleanBinding
import javafx.geometry.Orientation
import javafx.scene.Cursor
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.ToolBar
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.scene.media.MediaView
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.stage.Stage

import java.util.concurrent.Callable

class MainView(private val stage: Stage, private val player: Player) {
    private var root: BorderPane? = null
    private val tools: ToolBar
    private var timing: Text? = null
    private var media: MediaView? = null

    val node: Parent
        get() {
            root = BorderPane()
            root!!.top = makeTools()
            root!!.center = makeViewport()

            return root!!
        }

    init {
        this.tools = makeTools()
        stage.fullScreenProperty().addListener { _, _, n -> undoFullScreen(n) }
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

        val listener = ScaleListener(owner, player.context().canvas, media)
        owner.widthProperty().addListener(listener)
        owner.heightProperty().addListener(listener)
        listener.changed(null, 300, 300)

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
            player.play()
            return
        }
        if (event.button == MouseButton.SECONDARY)
            player.backward()
        else
            player.forward()
    }

    private fun makeTools(): ToolBar {
        val tools = ToolBar()
        tools.orientation = Orientation.HORIZONTAL

        timing = Text("00000000")
        timing!!.font = Font("Consolas", 30.0)
        timing!!.stroke = Color.BLUE
        timing!!.fill = Color.BLUE
        tools.items.add(timing)

        val full = Button("Full")
        full.setOnAction { stage.isFullScreen = true }
        tools.items.add(full)

        val home = Button("||<--")
        home.setOnAction { player.start() }
        tools.items.add(home)

        val oneOff = Button("OneOff")
        oneOff.setOnAction { oneOff() }
        tools.items.add(oneOff)

        val backwards = Button("<--")
        backwards.setOnAction { player.backward() }
        tools.items.add(backwards)

        val play = Button(">")
        play.setOnAction { player.play() }

        val isPlayingCallable = Callable { player.state == PlayerState.Playing }

        val trueIfPlaying = Bindings.createBooleanBinding(isPlayingCallable, player.stateProperty())
        play.disableProperty().bind(trueIfPlaying)
        tools.items.add(play)

        val playOne = Button(">|")
        playOne.setOnAction { player.playOne() }
        playOne.disableProperty().bind(trueIfPlaying)
        tools.items.add(playOne)

        val forwards = Button("-->")
        forwards.setOnAction { player.forward() }
        forwards.disableProperty().bind(trueIfPlaying)
        tools.items.add(forwards)

        val end = Button("-->||")
        end.setOnAction { player.end() }
        tools.items.add(end)

        val timinusTwo = Button("T-2")
        timinusTwo.setOnAction { player.penultimate() }
        tools.items.add(timinusTwo)

        val tminusOne = Button("T-1")
        tminusOne.setOnAction { player.ultimate() }
        tools.items.add(tminusOne)

        val markHere = Button("Mark")
        markHere.setOnAction { markHere(tools) }
        tools.items.add(markHere)

        return tools
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
        Platform.runLater { timing!!.text = newText }
    }

    private fun markHere(bar: ToolBar) {
        val text = Text(String.format("%8d", player.rhythm.beat() / 1000))
        text.font = Font("Consolas", 30.0)
        text.stroke = Color.BLUE
        text.fill = Color.BLUE
        bar.items.add(text)
    }

    private fun undoFullScreen(newValue: Boolean?) {
        if (newValue == false) {
            root!!.top = tools
            stage.scene.cursor = Cursor.DEFAULT
        } else {
            root!!.top = null
            stage.scene.cursor = Cursor.NONE
        }
    }


}