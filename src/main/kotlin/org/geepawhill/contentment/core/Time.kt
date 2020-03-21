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
 * Copyright 2015 Caprica Software Limited.
 */
package org.geepawhill.contentment.core

object Time {
    fun formatTime(value: Long): String {
        var value = value
        value /= 1000
        val hours = value.toInt() / 3600
        var remainder = value.toInt() - hours * 3600
        val minutes = remainder / 60
        remainder = remainder - minutes * 60
        val seconds = remainder
        return String.format("%d:%02d:%02d", hours, minutes, seconds)
    }
}