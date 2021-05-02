package com.pashcom.snake.tools

import java.net.URL
import javax.sound.sampled.AudioSystem

object SoundPlayer {

  def play(sound: URL): Unit = {
    val audioIn = AudioSystem.getAudioInputStream(sound)
    val clip = AudioSystem.getClip
    clip.open(audioIn)
    clip.start()
  }
}
