package com.github.bsfowlie.kotlin.tdd.snake.model

data class Bounds(
    val left: Int,
    val top: Int,
    val width: Int,
    val height: Int
) {

    init {
        require(width > 0) { "Width must be a non-zero positive value" }
        require(height > 0) { "Height must be a non-zero positive value" }
    }

    fun contains(position: Position) =
        left <= position.x && position.x < left + width
                && top <= position.y && position.y < top + height

}
