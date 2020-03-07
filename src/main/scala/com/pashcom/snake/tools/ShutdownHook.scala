package com.pashcom.snake.tools

object ShutdownHook {

  def set(action: Thread): Unit = {
    Runtime.getRuntime.addShutdownHook(action)
  }
}
