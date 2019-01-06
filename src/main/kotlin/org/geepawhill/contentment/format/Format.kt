package org.geepawhill.contentment.format

import javafx.scene.paint.Color
import javafx.scene.shape.Shape
import javafx.scene.text.Font
import org.geepawhill.contentment.style.Frames
import org.geepawhill.contentment.style.TypeFace
import org.geepawhill.contentment.utility.Names

class Format(val nickname: String, val base: Format?, vararg styles: Style) {

    private val overrides = mutableMapOf<String, Style>()

    constructor(vararg styles: Style) : this(Names.make("Unspecified" + Format::class.java.name), *styles) {}

    constructor(base: Format, vararg styles: Style) : this(Names.make("Unspecified" + Format::class.java.name), base, *styles) {}

    constructor(name: String, vararg styles: Style) : this(name, null, *styles) {}

    init {
        for (style in styles)
            put(style)
    }

    fun find(key: String): Style? {
        var candidate: Format? = this
        while (candidate != null) {
            val result = candidate.overrides[key]
            if (result != null) return result
            candidate = candidate.base
        }
        return null
    }

    fun put(style: Style) {
        overrides[style.key()] = style
    }

    fun require(key: String): Style {
        val result = find(key)
        if (result != null) return result
        throw MissingFormatException(key, nickname)
    }

    fun apply(key: String, shape: Shape) {
        require(key).apply(shape)
    }

    companion object {
        val DEFAULT = Format(
                "DEFAULT",
                TypeFace.color(Color.WHITE, 1.0),
                TypeFace.font(Font("Arial", 30.0), 1.0, 1.0),
                Frames.frame(Color.WHITE, 1.0, 1.0)
        )
    }

}
