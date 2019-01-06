package org.geepawhill.contentment.format

class Assumptions {
    internal var format = Format.DEFAULT

    fun format(): Format {
        return format
    }

    fun assume(format: Format) {
        this.format = format
    }

}
