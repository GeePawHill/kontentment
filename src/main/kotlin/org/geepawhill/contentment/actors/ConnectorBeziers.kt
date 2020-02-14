package org.geepawhill.contentment.actors

import org.geepawhill.contentment.geometry.Bezier

class ConnectorBeziers(
        val chosenMain: Bezier,
        val chosenFromTop: Bezier,
        val chosenFromBottom: Bezier,
        val chosenToTop: Bezier,
        val chosenToBottom: Bezier)
