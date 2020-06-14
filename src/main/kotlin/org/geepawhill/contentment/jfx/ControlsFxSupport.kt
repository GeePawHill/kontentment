package org.geepawhill.contentment.jfx

import javafx.event.EventTarget
import org.controlsfx.control.HiddenSidesPane
import tornadofx.*

fun EventTarget.hiddensidespane(op: HiddenSidesPane.() -> Unit = {}) = opcr(this, HiddenSidesPane(), op)