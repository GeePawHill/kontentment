package org.geepawhill.contentment.geometry

import java.util.function.Supplier

interface BezierSource : Supplier<Bezier> {
    companion object {
        fun value(bezier: Bezier): BezierSource {
            return object : BezierSource {
                override fun get(): Bezier {
                    return bezier
                }
            }
        }
    }
}
