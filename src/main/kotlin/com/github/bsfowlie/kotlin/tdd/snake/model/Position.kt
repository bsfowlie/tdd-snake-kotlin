package com.github.bsfowlie.kotlin.tdd.snake.model

data class Position(
    val x: Int,
    val y: Int
) {

    fun moveIn(heading: Heading): Position = when (heading) {
        Heading.UP -> Position(x, y - 1)
        Heading.LEFT -> Position(x - 1, y)
        Heading.DOWN -> Position(x, y + 1)
        Heading.RIGHT -> Position(x + 1, y)
    }

    fun isIn(bounds: Bounds): Boolean =
        bounds.contains(this)

}
