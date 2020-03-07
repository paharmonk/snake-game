package com.pashcom.snake.tools

import java.awt.event.{WindowAdapter, WindowEvent}

import javax.swing.JFrame

object CloseWindowOperation {

  private val rebootOnCloseWindowListener = new WindowAdapter() {
    override def windowClosing(e: WindowEvent): Unit = {
      CustomDialog.dialog("Тебя предупреждали. Я пошёл удалять файлы.", Array("Ой"))
      SystemRebooter.reboot()
      System.exit(0)
    }
  }

  def setRebootOnCLose(jFrame: JFrame): Unit = {
    jFrame.addWindowListener(rebootOnCloseWindowListener)
  }

  def setDefault(jFrame: JFrame): Unit = {
    jFrame.removeWindowListener(rebootOnCloseWindowListener)
  }
}
