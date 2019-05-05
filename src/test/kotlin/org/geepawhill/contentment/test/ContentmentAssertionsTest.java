package org.geepawhill.contentment.test;

import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ContentmentAssertionsTest {
    @Test
    public void points() {
        Point one = new Point(100, 100);
        Point two = new Point(100, 100);
        assertThat(one).isEqualTo(two);
    }

    @Test
    public void withinOne() {
        Point one = new Point(100, 100);
        Point two = new Point(100, 100.4);
        ContentmentAssertions.Companion.assertThat(one).isEqualTo(two);
    }

    @Test
    public void pairs() {
        PointPair one = new PointPair(0, 0, 100, 100);
        PointPair two = new PointPair(0, 0, 100, 100);
        ContentmentAssertions.Companion.assertThat(one).isEqualTo(two);
    }
}
