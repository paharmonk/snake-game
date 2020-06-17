package com.pashcom.snake

sealed abstract class GameStage

case object LOGO        extends GameStage
case object LOGO_TEXT   extends GameStage
case object INTRO       extends GameStage
case object LOGO2       extends GameStage
case object LOGO2_TEXT  extends GameStage
case object GAME_INTRO  extends GameStage
case object GAME        extends GameStage
