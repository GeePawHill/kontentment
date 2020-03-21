package org.geepawhill.contentment.core

import javafx.beans.value.ObservableValue
import javafx.geometry.Insets
import javafx.scene.control.Label
import javafx.scene.control.Slider
import javafx.scene.layout.BorderPane
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.Priority
import uk.co.caprica.vlcj.player.base.LibVlcConst
import uk.co.caprica.vlcj.player.base.MediaPlayer

internal class VideoControlsPane(mediaPlayer: MediaPlayer) : BorderPane() {
    private val hueLabel: Label
    private val saturationLabel: Label
    private val brightnessLabel: Label
    private val contrastLabel: Label
    private val hueSlider: Slider
    private val saturationSlider: Slider
    private val brightnessSlider: Slider
    private val contrastSlider: Slider

    class VideoControlLabel(caption: String) : Label(caption) {
        init {
            padding = Insets(8.0)
        }
    }

    class VideoControlSlider(min: Double, max: Double, value: Double) : Slider(min, max, value) {
        init {
            padding = Insets(8.0)
        }
    }

    init {
        val grid = GridPane()
        grid.padding = Insets(16.0)
        val labelColumn = ColumnConstraints()
        labelColumn.hgrow = Priority.NEVER
        val sliderColumn = ColumnConstraints()
        sliderColumn.hgrow = Priority.ALWAYS
        grid.columnConstraints.addAll(labelColumn, sliderColumn)
        hueLabel = VideoControlLabel("Hue")
        saturationLabel = VideoControlLabel("Saturation")
        brightnessLabel = VideoControlLabel("Brightness")
        contrastLabel = VideoControlLabel("Contrast")
        hueSlider = VideoControlSlider(LibVlcConst.MIN_HUE.toDouble(), LibVlcConst.MAX_HUE.toDouble(), mediaPlayer.video().hue().toDouble())
        saturationSlider = VideoControlSlider(LibVlcConst.MIN_SATURATION.toDouble(), LibVlcConst.MAX_SATURATION.toDouble(), mediaPlayer.video().saturation().toDouble())
        brightnessSlider = VideoControlSlider(LibVlcConst.MIN_BRIGHTNESS.toDouble(), LibVlcConst.MAX_BRIGHTNESS.toDouble(), mediaPlayer.video().brightness().toDouble())
        contrastSlider = VideoControlSlider(LibVlcConst.MIN_CONTRAST.toDouble(), LibVlcConst.MAX_CONTRAST.toDouble(), mediaPlayer.video().contrast().toDouble())
        GridPane.setConstraints(hueLabel, 0, 0)
        GridPane.setConstraints(saturationLabel, 0, 1)
        GridPane.setConstraints(brightnessLabel, 0, 2)
        GridPane.setConstraints(contrastLabel, 0, 3)
        GridPane.setConstraints(hueSlider, 1, 0)
        GridPane.setConstraints(saturationSlider, 1, 1)
        GridPane.setConstraints(brightnessSlider, 1, 2)
        GridPane.setConstraints(contrastSlider, 1, 3)
        grid.children.addAll(hueLabel, hueSlider)
        grid.children.addAll(saturationLabel, saturationSlider)
        grid.children.addAll(brightnessLabel, brightnessSlider)
        grid.children.addAll(contrastLabel, contrastSlider)
        center = grid
        hueSlider.valueProperty().addListener { obs: ObservableValue<out Number>?, oldValue: Number?, newValue: Number -> mediaPlayer.video().setHue(newValue.toFloat()) }
        saturationSlider.valueProperty().addListener { obs: ObservableValue<out Number>?, oldValue: Number?, newValue: Number -> mediaPlayer.video().setSaturation(newValue.toFloat()) }
        brightnessSlider.valueProperty().addListener { obs: ObservableValue<out Number>?, oldValue: Number?, newValue: Number -> mediaPlayer.video().setBrightness(newValue.toFloat()) }
        contrastSlider.valueProperty().addListener { obs: ObservableValue<out Number>?, oldValue: Number?, newValue: Number -> mediaPlayer.video().setContrast(newValue.toFloat()) }
    }
}