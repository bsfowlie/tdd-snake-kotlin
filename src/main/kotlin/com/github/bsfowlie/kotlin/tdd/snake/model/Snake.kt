package com.github.bsfowlie.kotlin.tdd.snake.model

class Snake(
    _head: Position,
    _direction: Heading
) {
    private val bodySegments: MutableList<Position> = mutableListOf(_head)
    private var previousTail: Position = _head

    val head
        get() = bodySegments[0]

    val length
        get() = bodySegments.size

    val body
        get() = bodySegments.toList()

    val isDead
        get() = bodySegments.lastIndexOf(head) != 0

    var direction = _direction
        set(value) {
            if (isDead) return
            field = value
        }

    fun move() {
        if (isDead) return
        val newHead = head.moveIn(direction)
        previousTail = bodySegments.removeAt(bodySegments.size - 1)
        bodySegments.add(0, newHead)
    }

    fun grow() {
        if (isDead) return
        bodySegments.add(previousTail)
    }

    fun isCollidingWith(food: Food): Boolean = head == food.position

    fun isInBounds(bounds: Bounds): Boolean = head.isIn(bounds)

}
