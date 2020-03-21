/*
 * This file is part of VLCJ.
 *
 * VLCJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VLCJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VLCJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2009, 2010, 2011, 2012, 2013, 2014, 2015 Caprica Software Limited.
 */
package org.geepawhill.contentment.core

import javafx.application.Platform
import javafx.beans.value.ObservableValue
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.Slider
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.TilePane
import javafx.scene.layout.VBox
import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A component containing simple media player controls.
 */
class ControlsPane(private val mediaPlayer: MediaPlayer) : VBox() {
    private val currentTimeLabel: Label
    private val timelineSlider: Slider
    private val durationLabel: Label
    private val playButton: Button
    private val pauseButton: Button
    private val stopButton: Button
    private val tracking = AtomicBoolean()
    private var clockTimer: Timer? = null
    private fun createButton(name: String, icon: String): Button {
        val button = Button()
        val url = String.format("/icons/buttons/%s.png", icon)
        val image = Image(javaClass.getResourceAsStream(url))
        button.graphic = ImageView(image)
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE)
        button.style = BUTTON_STYLE
        return button
    }

    private fun startTimer() {
        clockTimer = Timer()
        clockTimer!!.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                Platform.runLater { currentTimeLabel.text = Time.formatTime(mediaPlayer.status().time()) }
            }
        }, 0, 1000)
    }

    private fun stopTimer() {
        clockTimer!!.cancel()
    }

    private fun updateDuration(newValue: Long) {
        durationLabel.text = Time.formatTime(newValue)
    }

    @Synchronized
    private fun updateMediaPlayerPosition(newValue: Float) {
        if (tracking.get()) {
            mediaPlayer.controls().setPosition(newValue)
        }
    }

    @Synchronized
    private fun beginTracking() {
        tracking.set(true)
    }

    @Synchronized
    private fun endTracking() {
        tracking.set(false)
        // This deals with the case where there was an absolute click in the timeline rather than a drag
        mediaPlayer.controls().setPosition(timelineSlider.value.toFloat() / 100)
    }

    @Synchronized
    private fun updateSliderPosition(newValue: Float) {
        if (!tracking.get()) {
            timelineSlider.value = newValue * 100.toDouble()
        }
    }

    companion object {
        private const val COMPONENT_STYLE = "-fx-padding: 8; -fx-background-color: rgb(232, 232, 232);"
        private const val BUTTON_STYLE = "-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: black;"
    }

    init {
        currentTimeLabel = Label(Time.formatTime(0L))
        timelineSlider = Slider(0.0, 100.0, 0.0)
        timelineSlider.padding = Insets(8.0)
        durationLabel = Label(Time.formatTime(0L))
        val box = HBox()
        box.alignment = Pos.CENTER
        HBox.setHgrow(currentTimeLabel, Priority.NEVER)
        HBox.setHgrow(timelineSlider, Priority.ALWAYS)
        HBox.setHgrow(durationLabel, Priority.NEVER)
        box.children.addAll(currentTimeLabel, timelineSlider, durationLabel)
        val buttonsPane = TilePane()
        buttonsPane.padding = Insets(8.0)
        playButton = createButton("Play", "play")
        pauseButton = createButton("Pause", "pause")
        stopButton = createButton("Stop", "stop")
        style = COMPONENT_STYLE
        children.addAll(box, buttonsPane)
        buttonsPane.children.addAll(playButton, pauseButton, stopButton)
        playButton.onAction = EventHandler { actionEvent: ActionEvent? -> mediaPlayer.controls().play() }
        pauseButton.onAction = EventHandler { actionEvent: ActionEvent? -> mediaPlayer.controls().pause() }
        stopButton.onAction = EventHandler { actionEvent: ActionEvent? -> mediaPlayer.controls().stop() }
        mediaPlayer.events().addMediaPlayerEventListener(object : MediaPlayerEventAdapter() {
            override fun playing(mediaPlayer: MediaPlayer) {
                startTimer()
            }

            override fun paused(mediaPlayer: MediaPlayer) {
                stopTimer()
            }

            override fun stopped(mediaPlayer: MediaPlayer) {
                stopTimer()
            }

            override fun finished(mediaPlayer: MediaPlayer) {
                stopTimer()
            }

            override fun error(mediaPlayer: MediaPlayer) {
                stopTimer()
            }

            override fun lengthChanged(mediaPlayer: MediaPlayer, newLength: Long) {
                Platform.runLater { updateDuration(newLength) }
            }

            override fun positionChanged(mediaPlayer: MediaPlayer, newPosition: Float) {
                Platform.runLater { updateSliderPosition(newPosition) }
            }
        })
        timelineSlider.onMousePressed = EventHandler { mouseEvent: MouseEvent? -> beginTracking() }
        timelineSlider.onMouseReleased = EventHandler { mouseEvent: MouseEvent? -> endTracking() }
        timelineSlider.valueProperty().addListener { obs: ObservableValue<out Number>?, oldValue: Number?, newValue: Number -> updateMediaPlayerPosition(newValue.toFloat() / 100) }
    }
}