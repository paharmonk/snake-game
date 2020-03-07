package com.pashcom.snake.game

case class Point(var x: Int, var y: Int) {

  def this(point: Point) = {
    this(point.x, point.y)
  }

  def move(direction: Direction, value: Int): Unit = {
    direction match {
      case UP    => this.y -= value
      case DOWN  => this.y += value
      case RIGHT => this.x += value
      case LEFT  => this.x -= value
    }
  }

  def intersects(p: Point): Boolean = intersects(p, 10)

  def intersects(p: Point, tolerance: Int): Boolean = {
    val diffX = Math.abs(x - p.x)
    val diffY = Math.abs(y - p.y)
    this == p || (diffX <= tolerance && diffY <= tolerance)
  }
}
