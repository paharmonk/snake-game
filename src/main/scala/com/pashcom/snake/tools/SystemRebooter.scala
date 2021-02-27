package com.pashcom.snake.tools

object SystemRebooter {

  def reboot(): Unit = {
    val operatingSystem = System.getProperty("os.name").toLowerCase

    val shutdownCommand = operatingSystem match {
      case x if x.contains("windows") => "shutdown.exe -r -t 0"
      case _                          => "shutdown -r now"
    }

    if (shutdownCommand.nonEmpty) {
      Runtime.getRuntime.exec(shutdownCommand)
      System.exit(0)
    }
  }
}
