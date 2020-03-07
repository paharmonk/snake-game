package com.pashcom.snake.tools

import java.net.URL

import sun.audio.{AudioPlayer, AudioStream}

object SoundPlayer {

  def play(sound: URL): Unit = {
    val inputStream = sound.openStream()
    val audioStream = new AudioStream(inputStream)
    AudioPlayer.player.start(audioStream)
  }
}
