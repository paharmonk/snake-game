package com.pashcom.snake.game

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{Color, Dimension, Graphics, Image, Toolkit}

import com.pashcom.snake.tools.{DialogCollector, MediaLibrary, SoundPlayer, SystemRebooter}
import javax.swing.{JPanel, Timer}

import scala.annotation.tailrec
import scala.util.Random

class Board(timer: Timer, snake: Snake) extends JPanel with ActionListener {
  final private val B_WIDTH = 800
  final private val B_HEIGHT = 600
  final private val DOT_SIZE = 20
  final private val DELAY = 90

  private val GAME_WIN_SNAKE_LENGTH = 200
  private var gameStatus: GameStatus = RUNNING

  private var luntikPosition: Point = _
  private var grassPosition: List[Point] = List.empty
  private var displayBlood: Boolean = false

  private val ballImage: Image = MediaLibrary.balls.getImage
  private val luntikImage: Image = MediaLibrary.luntik.getImage
  private val headImage: Image = MediaLibrary.vupsen.getImage
  private val headImage2: Image = MediaLibrary.vupsenBoBo.getImage
  private val grassImage: Image = MediaLibrary.grass.getImage
  private val bloodImage: Image = MediaLibrary.blood.getImage

  initBoard()

  private def initBoard(): Unit = {
    setBackground(new Color(88, 144, 63))
    setFocusable(true)
    setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT))

    initGame()
  }

  private def initGame(): Unit = {
    locateLuntik()
    locateGrass()
    snake.reset()
  }

  def startGame(): Unit = {
    timer.setDelay(DELAY)
    timer.addActionListener(this)
  }

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    doDrawing(g)
  }

  private def doDrawing(g: Graphics): Unit = {
    gameStatus match {
      case RUNNING   => drawGame(g)
      case COLLISION => drawGame(g)
      case GAME_OVER => drawGameOver(g)
      case _         =>
    }

    Toolkit.getDefaultToolkit.sync()
  }

  private def drawGame(g: Graphics): Unit = {
    // рисуем лунтика
    g.drawImage(luntikImage, luntikPosition.x, luntikPosition.y, this)
    // рисуем змейку с конца, +20 для выравниваняи картинок
    snake.tail.reverseIterator.foreach(point => g.drawImage(ballImage, point.x, point.y + 20, this))

    // рисуем голову змейке. -3 для выравнивания картинок
    if (gameStatus == RUNNING) {
      g.drawImage(headImage, snake.head.x - 3, snake.head.y, this)
    } else {
      g.drawImage(headImage2, snake.head.x - 3, snake.head.y, this)
      gameStatus = GAME_OVER
    }

    // рисуем кровяку, -20 для выравниваняи картинок
    if (displayBlood) {
      g.drawImage(bloodImage, snake.head.x - 20, snake.head.y - 20, this)
    }

    // рисуем травку
    grassPosition.foreach(point => g.drawImage(grassImage, point.x, point.y, this))
  }

  private def drawGameOver(g: Graphics): Unit = {
    timer.stop()

    DialogCollector.dialogGameOver()
    SystemRebooter.reboot()
    System.exit(0)
  }

  private def checkLuntik(): Unit = {
    val head = snake.head

    if (head == luntikPosition || head.intersects(luntikPosition, DOT_SIZE * 2)) {
      snake.addTail()
      displayBlood = true
      SoundPlayer.play(MediaLibrary.deathSound)
      if (snake.tail.length < GAME_WIN_SNAKE_LENGTH) locateLuntik()
    } else {
      displayBlood = false
    }


    // ускорение в зависимости от длины змейки
    if (snake.tail.length == 40)
      timer.setDelay(DELAY - 20)

    if (snake.tail.length == 80)
      timer.setDelay(DELAY - 30)

    if (snake.tail.length == 100)
      timer.setDelay(DELAY - 40)

    // победа
    if (snake.tail.length == GAME_WIN_SNAKE_LENGTH && !displayBlood) {
      DialogCollector.dialogGameWin()
      System.exit(0)
    }
  }

  private def move(): Unit = {
    snake.move()
  }

  private def checkCollision(): Unit = {
    if (snake.head.x > B_WIDTH - 90 || snake.head.x < 0 || snake.head.y < 0 || snake.head.y > B_HEIGHT - 90) {
      gameStatus = COLLISION
    }

    if (snake.tail.contains(snake.head)) {
      gameStatus = COLLISION
    }

    if (gameStatus != RUNNING) {
      SoundPlayer.play(MediaLibrary.headToWall)
    }
  }

  @tailrec
  private def randomPoint: Point = {
    def getRandomCoord(bound: Int): Int = new Random().nextInt(bound)
    def check(p: Point, p2: Point): Boolean = p == p2 || p.intersects(p2, DOT_SIZE * 2)

    val point = Point(getRandomCoord(B_WIDTH - 118), getRandomCoord(B_HEIGHT - 150))
    if (!check(snake.head, point) && !snake.tail.forall(x => check(x, point)))
      point
    else
      randomPoint
  }

  private def locateLuntik(): Unit = {
    luntikPosition = randomPoint
  }

  private def locateGrass(): Unit = {
    for (_ <- 0 to 10) {
      grassPosition = grassPosition :+ randomPoint
    }
  }

  def actionPerformed(e: ActionEvent): Unit = {
    if (gameStatus == RUNNING) {
      checkLuntik()
      checkCollision()
      move()
    }

    repaint()
  }
}