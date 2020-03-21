package org.geepawhill.contentment.core

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.Cursor
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.ToolBar
import javafx.scene.media.MediaView
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.util.converter.IntegerStringConverter
import org.geepawhill.contentment.geometry.PointPair
import org.geepawhill.contentment.player.Player
import org.geepawhill.contentment.rhythm.Rhythm
import org.geepawhill.contentment.utility.JfxUtility
import tornadofx.*

class MainView() : View() {
    val player = Player()

    private lateinit var elapsed: Text

    private val startElapsedProperty = SimpleStringProperty("45")

    override val root = borderpane {
        elapsed = text {
            textProperty().bindBidirectional(player.elapsedProperty, IntegerStringConverter())
            font = Font.font(30.0)
            fill = Color.DARKBLUE
            stroke = Color.BLUE
            isVisible = false
        }
        top = toolbar {
            orientation = Orientation.HORIZONTAL
            text("00000000") {
                font = Font("Consolas", 30.0)
                stroke = Color.BLUE
                fill = Color.BLUE
                player.scriptProperty().addListener { _, _, _ ->
                    player.script.rhythm().beatProperty().addListener { _, _, beat ->
                        var newText = String.format("%8d", beat.toLong() / 1000)
                        if (beat.toLong() == 0L) text = "   Start"
                        if (beat.toLong() == Rhythm.MAX) text = "     End"
                        text = newText
                    }

                }
            }
            button("Full") {
                action { FX.primaryStage.isFullScreen = true }
            }
            button("||<--") {
                action { player.start() }
                disableWhen { player.isPlaying }
            }
            button("OneOff") {
                action { oneOff() }
            }
            button("<--") {
                action { player.backward() }
                disableWhen { player.isPlaying }
            }
            button(">") {
                action { player.play() }
                disableWhen { player.isPlaying }
            }
            button(">|") {
                action { player.playOne() }
                disableWhen { player.isPlaying }
            }
            button("-->") {
                action { player.forward() }
                disableWhen { player.isPlaying }
            }
            button("-->||") {
                action { player.end() }
                disableWhen { player.isPlaying }
            }
            button("T-2") {
                action { player.penultimate() }
                disableWhen { player.isPlaying }
            }
            button("T-1") {
                action { player.ultimate() }
                disableWhen { player.isPlaying }
            }
            button("Mark") {
                action { markHere(this@toolbar) }
                enableWhen { player.isPlaying }
            }
            region { prefWidth = 100.0 }
            checkbox {
                isSelected = false
                action {
                    if (isSelected) {
                        player.elapsedProperty.value = startElapsedProperty.value.toInt()
                        elapsed.isVisible = true
                    } else elapsed.isVisible = false
                }
            }
            textfield(startElapsedProperty)
        }
        center = ContentView(player).root
    }

    private var media: MediaView? = null

    init {
        preloadFontFile("/org/geepawhill/scripts/ChewedPenBB.otf")
        preloadFontFile("/org/geepawhill/scripts/ChewedPenBB_ital.otf")
        FX.primaryStage.isMaximized = true
        FX.primaryStage.fullScreenExitHint = ""

        player.load(BicScript().make())
        root.widthProperty().addListener { _, _, _ ->
            elapsed.x = root.width - 100.0
            elapsed.y = root.height - 20.0
        }
        root.children.add(elapsed)
        FX.primaryStage.fullScreenProperty().addListener { _, _, n -> fullscreenChanged(n!!) }
    }

    private fun preloadFontFile(fontfile: String) {
        Font.loadFont(Main::class.java.getResource(fontfile).toExternalForm(), 50.0)
    }

    private fun oneOff() {
        JfxUtility.capture(FX.primaryStage.scene.root)
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
            FX.primaryStage.scene.cursor = Cursor.DEFAULT
        } else {
            root.top.hide()
            FX.primaryStage.scene.cursor = Cursor.NONE
        }
    }


}
