package org.geepawhill.contentment.fragments

import javafx.scene.Group
import javafx.scene.shape.CubicCurveTo
import javafx.scene.shape.MoveTo
import javafx.scene.shape.Path
import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.contentment.core.Context
import org.geepawhill.contentment.geometry.Bezier
import org.geepawhill.contentment.geometry.BezierSource
import org.geepawhill.contentment.geometry.Point
import org.geepawhill.contentment.geometry.PointPair
import test.ContentmentAssertions.Companion.assertThat
import org.junit.jupiter.api.Test

class MarkTest {
    private val group = Group()
    private val bezier = Bezier(PointPair(0.0, 0.0, 100.0, 100.0))
    private val context = Context()

    @Test
    fun addsPath() {
        val mark = Mark(group, BezierSource.value(bezier))
        mark.prepare(context)
        assertThat(group.children.size).isEqualTo(1)
    }

    @Test
    fun pathClearedAtZero() {
        val mark = Mark(group, BezierSource.value(bezier))
        mark.prepare(context)
        assertThat(group.children.size).isEqualTo(1)
        val path = group.children[0] as Path
        assertThat(path.elements.size).isEqualTo(0)
    }


    @Test
    fun addsTwoStepPath() {
        val mark = Mark(group, BezierSource.value(bezier))
        mark.prepare(context)
        mark.interpolate(context, .25)
        assertThat(group.children.size).isEqualTo(1)
        val path = group.children[0] as Path
        assertThat(path.elements.size).isEqualTo(2)
        assertThat(path.elements[0]).isInstanceOf(MoveTo::class.java)
        assertThat(path.elements[1]).isInstanceOf(CubicCurveTo::class.java)
    }

    @Test
    fun completedPathValues() {
        val mark = Mark(group, BezierSource.value(bezier))
        mark.prepare(context)
        mark.interpolate(context, 1.0)
        val path = group.children[0] as Path
        val moveTo = path.elements[0] as MoveTo
        assertThat(Point(moveTo.x, moveTo.y)).isEqualTo(Point(0.0, 0.0))
        val curveTo = path.elements[1] as CubicCurveTo
        assertThat(Point(curveTo.x, curveTo.y)).isEqualTo(Point(100.0, 100.0))
    }

    @Test
    fun partialPathValues() {
        val mark = Mark(group, BezierSource.value(bezier))
        mark.prepare(context)
        mark.interpolate(context, .5)
        val path = group.children[0] as Path
        val moveTo = path.elements[0] as MoveTo
        assertThat(Point(moveTo.x, moveTo.y)).isEqualTo(Point(0.0, 0.0))
        val curveTo = path.elements[1] as CubicCurveTo
        assertThat(Point(curveTo.x, curveTo.y)).isEqualTo(Point(50.0, 50.0))
    }
}
