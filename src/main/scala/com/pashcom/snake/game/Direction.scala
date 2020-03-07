package com.pashcom.snake.game

sealed abstract class Direction {

  def isX: Boolean = this match {
    case x if x == LEFT || x == RIGHT => true
    case _ => false
  }

  def isY: Boolean = this match {
    case x if x == UP || x == DOWN => true
    case _ => false
  }
}

case object LEFT extends Direction
case object RIGHT extends Direction
case object UP extends Direction
case object DOWN extends Direction