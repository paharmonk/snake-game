package com.pashcom.snake.game

sealed abstract class GameStatus

case object NOT_STARTED extends GameStatus
case object RUNNING extends GameStatus
case object COLLISION extends GameStatus
case object PAUSED extends GameStatus
case object GAME_OVER extends GameStatus