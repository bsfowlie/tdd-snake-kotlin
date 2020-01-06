package com.github.bsfowlie.kotlin.tdd.snake.model

data class Position(
    private val x: Int,
    private val y: Int
) {

    fun x(): Int = x

    fun y(): Int = y

    fun moveIn(heading: Heading): Position = when (heading) {
        Heading.UP -> Position(x, y - 1)
        Heading.LEFT -> Position(x - 1, y)
        Heading.DOWN -> Position(x, y + 1)
        Heading.RIGHT -> Position(x + 1, y)
    }
}
