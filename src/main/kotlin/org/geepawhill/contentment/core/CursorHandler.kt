package org.geepawhill.contentment.core

import javafx.application.Platform
import javafx.event.EventHandler
import javafx.scene.Cursor
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A simple component to manage hiding the mouse pointer over a [Node] after a period of inactivity.
 *
 *
 * This is somewhat of a crude implementation, we could do things like stopping/suspending timers if the pointer moves
 * inside/outside the managed component, don't hide the pointer if there is no video playing, and so forth, but it is
 * quite simple how it is and is good enough for demo purposes.
 */
class CursorHandler(private val node: Node, private val timeout: Long) {
    private val timer: Timer

    @Volatile
    private var lastMouse: Long = 0
    private val hidden = AtomicBoolean(false)
    fun start() {
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                if (!hidden.get() && System.currentTimeMillis() - lastMouse > timeout) {
                    Platform.runLater { hideCursor() }
                }
            }
        }, 1000, 1000)
    }

    @Synchronized
    private fun hideCursor() {
        hidden.set(true)
        node.cursor = Cursor.NONE
    }

    @Synchronized
    private fun showCursor() {
        hidden.set(false)
        node.cursor = Cursor.DEFAULT
        lastMouse = System.currentTimeMillis()
    }

    init {
        timer = Timer()
        node.onMouseMoved = EventHandler { mouseEvent: MouseEvent? -> showCursor() }
    }
}