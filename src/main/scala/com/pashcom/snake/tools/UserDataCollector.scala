package com.pashcom.snake.tools

import java.io.File
import java.nio.file.Paths

object UserDataCollector {

  def isRu: Boolean = System.getProperty("user.language").toLowerCase == "ru"

  private def getLastFilesFromDir(dir: File, lastCount: Int): List[String] = {
    if (dir.exists())
      dir.listFiles()
         .filter(_.isFile)
         .sortWith((f1, f2) => f1.lastModified() > f2.lastModified())
         .map(_.getName)
         .map(nameShorter)
         .take(lastCount)
         .toList
    else
      List.empty
  }

  def findUserFiles: List[String] = {
    val etc = if (isRu) "и т.д." else "etc."

    val homeDir = System.getProperty("user.home")
    val downloadDir = Paths.get(homeDir, "Downloads")
    val documentsDir = Paths.get(homeDir, "Documents")
    val picturesDir = Paths.get(homeDir, "Pictures")

    getLastFilesFromDir(downloadDir.toFile, 5) ++
      getLastFilesFromDir(documentsDir.toFile, 5) ++
      getLastFilesFromDir(picturesDir.toFile, 2) :+ etc
  }

  def getUserName: String = System.getProperty("user.name")

  def getUserDrives: List[String] = {
    File.listRoots.map(_.toString).toList
  }

  private def nameShorter(name: String): String = {
    val getExt = (name: String) => {
      if (name.contains(".")) name.substring(name.lastIndexOf(".") + 1, name.length)
      else ""
    }

    name match {
      case x if x.length > 40 => x.substring(0, 40) + "..." + getExt(x)
      case x => x
    }
  }
}
