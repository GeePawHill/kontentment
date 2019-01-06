package org.geepawhill.contentment.utility

object Names {

    private val increments = mutableMapOf<String, Int>()

    fun <T> make(clazz: Class<T>): String {
        return make(clazz.simpleName)
    }

    fun make(`object`: Any): String {
        return make(`object`.javaClass.simpleName)
    }

    fun make(simpleName: String): String {
        val increment = increments.getOrDefault(simpleName, 1)
        increments[simpleName] = increment + 1
        return simpleName + "_" + increment
    }

    fun reset() {
        increments.clear()
    }

}
