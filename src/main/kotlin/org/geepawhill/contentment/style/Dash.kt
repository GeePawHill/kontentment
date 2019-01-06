package org.geepawhill.contentment.style

class Dash private constructor(vararg array: Double) {
    val array: Array<Double> = array.toTypedArray()

    companion object {

        fun solid(): Dash {
            return Dash(0.0)
        }

        fun dash(vararg dash: Double): Dash {
            return Dash(*dash)
        }
    }

}
