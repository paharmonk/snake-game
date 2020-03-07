package com.pashcom.snake.game

import java.awt.event.{KeyAdapter, KeyEvent}

class SnakeMoveKeyAdapter(snake: Snake) extends KeyAdapter {

  override def keyPressed(e: KeyEvent): Unit = {
    e.getKeyCode match {
      case KeyEvent.VK_LEFT   => snake.turn(LEFT)
      case KeyEvent.VK_RIGHT  => snake.turn(RIGHT)
      case KeyEvent.VK_UP     => snake.turn(UP)
      case KeyEvent.VK_DOWN   => snake.turn(DOWN)
      case _ =>
    }
  }
}
