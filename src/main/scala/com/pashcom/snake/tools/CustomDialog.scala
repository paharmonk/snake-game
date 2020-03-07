package com.pashcom.snake.tools

import java.awt.Component

import javax.swing.{Icon, JOptionPane, WindowConstants}

object CustomDialog {

  def dialog(question: String, answers: Array[String], title: String = ""): Int = {
    showOptionDialogDisabledCloseButton(
      null,
      question,
      title,
      0,
      3,
      EmptyIcon,
      answers.asInstanceOf[Array[AnyRef]])
  }

  // диалог, который нельзя закрыть крестиком
  private def showOptionDialogDisabledCloseButton(parentComponent: Component,
                                                  message: Any,
                                                  title: String,
                                                  optionType: Int,
                                                  messageType: Int,
                                                  icon: Icon,
                                                  options: Array[AnyRef]): Int = {
    val pane = new JOptionPane(message, messageType, optionType, icon, options, null)

    pane.setComponentOrientation(Option(parentComponent).getOrElse(JOptionPane.getRootFrame)
                                                        .getComponentOrientation)
    val dialog = pane.createDialog(parentComponent, title)
    dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE)

    dialog.setVisible(true)
    dialog.dispose()

    val selectedValue = pane.getValue

    if (selectedValue == null) return -1
    if (options == null) {
      selectedValue match {
        case integer: Integer => return integer.intValue
        case _ => return -1
      }
    }

    var counter = 0
    val maxCounter = options.length
    while ( {
      counter < maxCounter
    }) {
      if (options(counter) == selectedValue) return counter
      counter += 1
    }
    -1
  }

  case object EmptyIcon extends Icon {
    def getIconHeight: Int = 0
    def getIconWidth: Int = 0
    def paintIcon(c: java.awt.Component, g: java.awt.Graphics, x: Int, y: Int): Unit = ()
  }
}
