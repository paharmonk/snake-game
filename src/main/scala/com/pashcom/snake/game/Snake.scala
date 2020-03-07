package com.pashcom.snake.game

import scala.collection.mutable.ArrayBuffer

class Snake(x: Int,
            y: Int,
            initialSize: Int,
            var direction: Direction,
            dotSize: Int) {
  var head: Point = _
  var tail: ArrayBuffer[Point] = _
  init()

  private def init(): Unit = {
    head = Point(x, y)
    tail = ArrayBuffer[Point]()

    for (_ <- 1 to initialSize) {
      tail.addOne(Point(x - 10, y))
    }
  }

  def reset(): Unit = init()

  def move(): Unit = {
    val newTail = ArrayBuffer[Point]()

    for (i <- tail.indices) {
      val prev = if (i == 0) this.head else this.tail(i - 1)
      newTail.addOne(Point(prev.x, prev.y))
    }

    this.tail = newTail
    this.head.move(this.direction, dotSize)
  }

  def addTail(): Unit = {
    this.tail.addOne(Point(-10, -10))
  }

  def turn(newDirection: Direction): Unit = {
    if (newDirection.isX && direction.isY || newDirection.isY && direction.isX)
      direction = newDirection
  }
}
