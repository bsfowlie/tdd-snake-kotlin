package com.github.bsfowlie.kotlin.tdd.snake.model

class Snake(
    head: Position,
    private var direction: Heading
) {
    private val body: MutableList<Position> = mutableListOf(head)
    private var previousTail: Position = head

    fun move() {
        if (isDead()) return
        val newHead = head().moveIn(direction())
        previousTail = body.removeAt(body.size - 1)
        body.add(0, newHead)
    }

    fun changeHeading(newHeading: Heading) {
        if (isDead()) return
        direction = newHeading
    }

    fun grow() {
        if (isDead()) return
        body.add(previousTail)
    }

    fun head(): Position = body[0]

    fun direction(): Heading = direction

    fun length(): Int = body.size

    fun body(): List<Position> = body.toList()

    fun isCollidingWith(food: Food): Boolean = head() == food.position()

    fun isInBounds(bounds: Bounds): Boolean = bounds.contains(head())

    fun isDead(): Boolean = body.lastIndexOf(head()) != 0

}
