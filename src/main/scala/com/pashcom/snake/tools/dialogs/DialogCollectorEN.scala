package com.pashcom.snake.tools.dialogs

import com.pashcom.snake.tools.UserDataCollector

import scala.annotation.tailrec

object DialogCollectorEN extends DialogCollector {

  def dialogStartGame(): Unit = {
    var res = CustomDialog.dialog(s"Greetings and welcome ${UserDataCollector.getUserName}. I want to play a game.",
      Array("Ok", "I don't want. Get lost."))

    if (res == 1) {
      CustomDialog.dialog("Haha. You have no choice. And try to be a little polite.",
        Array("Who are you?"))

      CustomDialog.dialog("I'm Jigsaw. This is my game. I give you a try to play.",
        Array("I guess you won't leave me alone..."))
    }

    CustomDialog.dialog(s"I looked around here, you have many interesting files.",
      Array("Really?"))

    CustomDialog.dialog(s"So we have" +
      s"\n\n${UserDataCollector.findUserFiles.mkString("\n")}",
      Array("Stop. Have you got into my computer??? Are you virus?"))

    res = CustomDialog.dialog(s"No, I'm not a virus. But I can delete your files like a virus." +
      s"\n\nWhat else do we have here? A lot of things, even if I want I will can't display all your stuff:" +
      s"\n${UserDataCollector.getUserDrives.mkString("\n")}" +
      s"\n\nSo, if you lose my game, I will delete some file in some directory. Or in many directories. Ha-ha.",
      Array("I don't care. I wanted to delete these files a long times ago.", "AAA. You are insane!"))

    var extraText = if (res == 0) "One more thing... If you change your mind about your files or you want to take care about them."
                    else "One more thing..."

    res = CustomDialog.dialog(s"$extraText\n\nDo you feel yourself smart guy? You just close " +
      s"\nmy game and that's all? I will make you happy: this doesn't work." +
      s"\nMaybe I changed behaviour of close button" +
      s"\nand maybe I added extra hook on game process termination." +
      s"\n\nSo, I would not recommend close this game window, otherwise YOU WILL LOSE YOUR FILES.",
      Array("You are evil genius. Respect.", "You are bad. I will tell my grandmother about you."))

    extraText = if (res == 1) "Good kid, check your granny. When did you visit her last time?\n\n"
                else ""

    CustomDialog.dialog(s"${extraText}Ok. Let's go.",
      Array("Let's go", "Let's go"))
  }

  def dialogGameOver(): Unit = {
    val res = CustomDialog.dialog("New game?",
      Array("Ok", "Exit"))

    val extraText = if (res == 0) "Ahahahaha. You are the fool. You can't start new game.\n\n"
                    else ""

    CustomDialog.dialog(s"$extraText There were many warnings. I start file removing. Ciao.",
      Array("Ok"))
  }

  def dialogGameWin(): Unit = {
    CustomDialog.dialog(s"${UserDataCollector.getUserName}," +
      s"\nIt's impossible but you won." +
      s"\nAll Luntiks were destroyed successfully." +
      s"\nCome again tomorrow for new Luntiks." +
      s"\n\nCongrats. You are cool guy. You can say about it to all your friends.",
      Array("Exit"))
  }

  override def dialogHowToPlay(): Unit = {
    @tailrec
    def showDialog(extraText: String): Unit = {
      val res = CustomDialog.dialog(s"$extraText\nUse arrow keys on keyboard to control the snake.",
        Array("Ok", "I don't understand"))

      if (res == 1) showDialog("Don't worry. I'll expalain you one more time.")
    }

    showDialog("")
  }
}
