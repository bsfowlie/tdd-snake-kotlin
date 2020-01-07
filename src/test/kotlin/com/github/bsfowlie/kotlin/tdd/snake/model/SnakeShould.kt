package com.github.bsfowlie.kotlin.tdd.snake.model

import org.assertj.core.api.WithAssertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class SnakeShould : WithAssertions {

    @Test
    fun `have an initial position`() {
        // given
        val snake = Snake(Position(1, 1), Heading.RIGHT)

        // when

        // then
        assertThat(snake.head).isEqualTo(Position(1, 1))
    }

    @Test
    fun `have an initial direction`() {
        // given
        val snake = Snake(Position(2, 2), Heading.DOWN)

        // when

        // then
        assertThat(snake.direction).isEqualTo(Heading.DOWN)
    }

    @Test
    fun `change direction`() {
        // given
        val snake = Snake(Position(0, 1), Heading.UP)

        // when
        snake.direction = Heading.DOWN

        // then
        assertThat(snake.direction).isEqualTo(Heading.DOWN)
    }

    @Test
    fun `be able to move right`() {
        // given
        val snake = Snake(Position(1, 1), Heading.RIGHT)

        // when
        snake.move()

        // then
        assertThat(snake.head).isEqualTo(Position(2, 1))
    }

    @Test
    fun `be able to move down`() {
        // given
        val snake = Snake(Position(1, 1), Heading.DOWN)

        // when
        snake.move()

        // then
        assertThat(snake.head).isEqualTo(Position(1, 2))
    }

    @Test
    fun `be able to move left`() {
        // given
        val snake = Snake(Position(1, 1), Heading.LEFT)

        // when
        snake.move()

        // then
        assertThat(snake.head).isEqualTo(Position(0, 1))
    }

    @Test
    fun `be able to move up`() {
        // given
        val snake = Snake(Position(1, 1), Heading.UP)

        // when
        snake.move()

        // then
        assertThat(snake.head).isEqualTo(Position(1, 0))
    }

    @Test
    fun `have a length`() {
        // given
        val snake = Snake(Position(0, 0), Heading.LEFT)

        // when

        // then
        assertThat(snake.length).isEqualTo(1)
    }

    @Test
    fun `be able to grow in length`() {
        // given
        val snake = Snake(Position(3, 5), Heading.DOWN)
        snake.move()

        // when
        snake.grow()

        // then
        assertThat(snake.length).isEqualTo(2)
    }

    @Test
    fun `have a body with two positions after growing`() {
        // given
        val snake = Snake(Position(3, 5), Heading.DOWN)
        snake.move()

        // when
        snake.grow()

        // then
        assertThat(snake.body).containsExactly(Position(3, 6), Position(3, 5))
    }

    @Test
    fun `detect colliding with food`() {
        // given
        val snake = Snake(Position(5, 5), Heading.LEFT)
        val food = Food(Position(5, 5))

        // when

        // then
        assertThat(snake.isCollidingWith(food)).isTrue()
    }

    @Test
    fun `detect not colliding with food`() {
        // given
        val snake = Snake(Position(5, 5), Heading.UP)
        val food = Food(Position(5, 6))

        // when

        // then
        assertThat(snake.isCollidingWith(food)).isFalse()
    }

    @Test
    fun `detect that it is in bounds`() {
        // given
        val snake = Snake(Position(1, 1), Heading.RIGHT)
        val bounds = Bounds(1, 1, 1, 1)

        // when

        // then
        assertThat(snake.isInBounds(bounds)).isTrue()
    }

    @Test
    fun `detect that it is out of bounds LEFT`() {
        // given
        val snake = Snake(Position(0, 1), Heading.LEFT)
        val bounds = Bounds(1, 1, 1, 1)

        // when

        // then
        assertThat(snake.isInBounds(bounds)).isFalse()
    }

    @Test
    fun `detect that it is out of bounds UP`() {
        // given
        val snake = Snake(Position(1, 0), Heading.UP)
        val bounds = Bounds(1, 1, 1, 1)

        // when

        // then
        assertThat(snake.isInBounds(bounds)).isFalse()
    }

    @Test
    fun `detect that it is out of bounds RIGHT`() {
        // given
        val snake = Snake(Position(2, 1), Heading.RIGHT)
        val bounds = Bounds(1, 1, 1, 1)

        // when

        // then
        assertThat(snake.isInBounds(bounds)).isFalse()
    }

    @Test
    fun `detect that it is out of bounds DOWN`() {
        // given
        val snake = Snake(Position(1, 2), Heading.DOWN)
        val bounds = Bounds(1, 1, 1, 1)

        // when

        // then
        assertThat(snake.isInBounds(bounds)).isFalse()
    }

    @Test
    fun `be alive`() {
        // given
        val snake = Snake(Position(1, 1), Heading.RIGHT)

        // when

        // then
        assertThat(snake.isDead).isFalse()
    }

    @Test
    fun `be able to die`() {
        // given
        val snake = Snake(Position(1, 1), Heading.RIGHT)
        (1..4).forEach { _ ->
            snake.move()
            snake.grow()
        }
        snake.direction = Heading.DOWN
        snake.move()
        snake.direction = Heading.LEFT
        snake.move()
        snake.direction = Heading.UP
        assertThat(snake.isDead).describedAs("Snake should not have collided with self yet").isFalse()

        // when
        snake.move()

        // then
        assertThat(snake.isDead).describedAs("Snake should have collided with self").isTrue()
    }

    @Test fun `cannot move after dying`() {
        // given
        val snake = Snake(Position(2, 1), Heading.DOWN)
        snake.move()
        snake.grow()
        snake.move()
        snake.grow()
        snake.direction = Heading.UP
        snake.move()
        assertThat(snake.isDead).describedAs("Snake should die after colliding with self").isTrue()

        // when
        snake.direction = Heading.RIGHT
        snake.move()
        snake.grow()

        // then
        assertAll(
            {assertThat(snake.isDead).describedAs("Snake should still be dead").isTrue()},
            {assertThat(snake.direction).describedAs("A dead snake cannot change direction").isEqualTo(Heading.UP)},
            {assertThat(snake.head).describedAs("A dead snake cannot move").isEqualTo(Position(2,2))},
            {assertThat(snake.length).describedAs("A dead snake cannot grow").isEqualTo(3)}
        )
    }

}
