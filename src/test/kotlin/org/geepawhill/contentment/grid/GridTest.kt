package org.geepawhill.contentment.grid

import org.assertj.core.data.Offset
import org.geepawhill.contentment.geometry.PointPair
import org.junit.jupiter.api.Test

import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.contentment.test.ContentmentAssertions.assertThat

class GridTest {
    private val bounds = PointPair(0.0, 100.0, 300.0, 500.0)
    private val grid = Grid(bounds)

    @Test
    fun gridHasBorders() {
        assertThat(grid.top().points()).isEqualTo(bounds.northLine())
        assertThat(grid.left().points()).isEqualTo(bounds.westLine())
        assertThat(grid.bottom().points()).isEqualTo(bounds.southLine())
        assertThat(grid.right().points()).isEqualTo(bounds.eastLine())
    }

    @Test
    fun verticalAndHorizontal() {
        assertThat(grid.vertical(50).points()).isEqualTo(bounds.xCenterLine())
        assertThat(grid.vertical(25).points().from.x).isCloseTo(bounds.along(.25).x, Offset.offset(1.0))
        assertThat(grid.horizontal(50).points()).isEqualTo(bounds.yCenterLine())
        assertThat(grid.horizontal(25).points().from.y).isCloseTo(bounds.along(.25).y, Offset.offset(1.0))
    }

    @Test
    fun point() {
        assertThat(grid.point(grid.vertical(50), grid.horizontal(50))).isEqualTo(bounds.center())
        assertThat(grid.point(grid.vertical(33), grid.horizontal(33))).isEqualTo(bounds.along(.33))
    }

    @Test
    fun area() {
        assertThat(grid.area(grid.left(), grid.top(), grid.right(), grid.bottom())).isEqualTo(bounds)
        val centerThird = PointPair(bounds.along(.33), bounds.along(.66))
        assertThat(grid.area(grid.vertical(33), grid.horizontal(33), grid.vertical(66), grid.horizontal(66))).isEqualTo(centerThird)
    }

    @Test
    fun nested() {
        val square = Grid(0.0, 0.0, 100.0, 100.0)
        val newTop = square.horizontal(25)
        val newBottom = square.horizontal(50)
        val newLeft = square.vertical(33)
        val newRight = square.vertical(66)
        val nested = square.nested(newLeft, newTop, newRight, newBottom)
        assertThat(nested.all()).isEqualTo(PointPair(33.0, 25.0, 66.0, 50.0))
    }
}
