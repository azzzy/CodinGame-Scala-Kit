package com.truelaurel.samplegames.wondev.arena

import com.truelaurel.codingame.challenge.{GameArena, GameResult}
import com.truelaurel.samplegames.wondev.analysis.WondevAnalysis
import com.truelaurel.samplegames.wondev.domain.{MoveBuild, MovePush, WondevAction, WondevState}

/**
  * Created by hwang on 24/06/2017.
  */
object WondevArena extends GameArena[WondevState, WondevAction] {
  override def next(fromState: WondevState, actions: Vector[WondevAction]): WondevState = {
    val action = actions.head
    action match {
      case MoveBuild(unitIndex, moveDir, buildDir) =>
        val unit = fromState.myUnits(unitIndex)
        val moveTarget = WondevAnalysis.neighborIn(unit, moveDir)
        val buildTarget = WondevAnalysis.neighborIn(moveTarget, buildDir)

        fromState.copy(turn = fromState.turn + 1, myUnits = Vector(moveTarget),
          heights = fromState.heights.updated(buildTarget, fromState.heights(buildTarget) + 1))

      case MovePush(unit, moveDir, pushDir) => fromState
    }


  }

  override def judge(state: WondevState): GameResult = ???
}
