package com.pashcom.snake

import java.awt.event.{ActionEvent, ActionListener}

import com.pashcom.snake.game.{Board, RIGHT, Snake, SnakeMoveKeyAdapter}
import com.pashcom.snake.tools.{CloseWindowOperation, DialogCollector, MediaLibrary, SoundPlayer}
import javax.swing._

class GameFrame extends JFrame {
  private var gameStage: GameStage = LOGO
  private var board: Board = _
  private val gameIntroActionListener = getGameIntroActionListener
  private val label = new JLabel()
  private val timer = new Timer(1500, gameIntroActionListener)

  setResizable(false)
  pack()
  setTitle("Snake")
  setSize(800, 600)
  setLocationRelativeTo(null)
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  startIntro()

  private def startIntro(): Unit = {
    label.setIcon(MediaLibrary.pashcomLogo)
    add(label)
    timer.start()
  }

  private def startGame(): Unit = {
    timer.removeActionListener(gameIntroActionListener)

    val snake = new Snake(240, 160, 3, RIGHT, 20)
    addKeyListener(new SnakeMoveKeyAdapter(snake))

    board = new Board(timer, snake)
    board.startGame()

    add(board)
  }

  private def getGameIntroActionListener: ActionListener = {
    val actionListener: ActionListener = (e: ActionEvent) => {
      gameStage match {
        case LOGO =>
          gameStage = LOGO_TEXT
          label.setIcon(MediaLibrary.introText)

        case LOGO_TEXT =>
          gameStage = INTRO
          label.setIcon(MediaLibrary.sawDoll)

        case INTRO =>
          timer.stop()
          DialogCollector.dialogStartGame()
          CloseWindowOperation.setRebootOnCLose(this)
          label.setIcon(MediaLibrary.pashcomLogo)
          gameStage = LOGO2
          timer.start()

        case LOGO2 =>
          gameStage = LOGO2_TEXT
          label.setIcon(MediaLibrary.introText)

        case LOGO2_TEXT =>
          gameStage = GAME_INTRO
          SoundPlayer.play(MediaLibrary.introAudio)
          label.setIcon(MediaLibrary.introGif)
          timer.setDelay(6030)  // время introGif

        case GAME_INTRO =>
          timer.setDelay(1500)
          gameStage = GAME

        case GAME =>
          label.setVisible(false)
          remove(label)
          startGame()

        case _ =>
      }
    }

    actionListener
  }
}
