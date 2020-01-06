package com.github.bsfowlie.kotlin.tdd.snake.model

data class Bounds(
    private val left: Int,
    private val top: Int,
    private val width: Int,
    private val height: Int
) {

    fun contains(position: Position): Boolean {
        return left <= position.x() && position.x() < left + width
                && top <= position.y() && position.y() < top + height
    }

}
