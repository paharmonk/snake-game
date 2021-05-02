package com.pashcom.snake.tools.dialogs

import com.pashcom.snake.tools.UserDataCollector

trait DialogCollector {
  def dialogStartGame()
  def dialogHowToPlay()
  def dialogGameOver()
  def dialogGameWin()
}

object DialogCollector {
  def getDialogCollector: DialogCollector = {
    if (UserDataCollector.isRu) DialogCollectorRU
    else                        DialogCollectorEN
  }
}
