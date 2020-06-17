package com.pashcom.snake.tools

import java.net.URL

import javax.swing.ImageIcon

object MediaLibrary {

  // sound
  val introAudio: URL = getResource("luntik_intro.wav")
  val headToWall: URL = getResource("head_to_wall.wav")
  val deathSound: URL = getResource("death_sound.wav")

  // sprite
  val blood: ImageIcon = new ImageIcon(getResource("blood.png"))
  val grass: ImageIcon = new ImageIcon(getResource("grass.png"))
  val balls: ImageIcon = new ImageIcon(getResource("balls.png"))
  val luntik: ImageIcon = new ImageIcon(getResource("luntik.png"))
  val vupsen: ImageIcon = new ImageIcon(getResource("vupsen.png"))
  val vupsenBoBo: ImageIcon = new ImageIcon(getResource("vupsen_bobo.png"))

  // intro
  val introGif: ImageIcon = new ImageIcon(getResource("luntik_intro.gif"))
  val introText: ImageIcon = new ImageIcon(getResource("intro_text.jpg"))
  val sawDoll: ImageIcon = new ImageIcon(getResource("saw_doll.jpg"))
  val pashcomLogo: ImageIcon = new ImageIcon(getResource("pashcom_logo.jpg"))

  private def getResource(name: String): URL = this.getClass.getClassLoader.getResource(name)
}
