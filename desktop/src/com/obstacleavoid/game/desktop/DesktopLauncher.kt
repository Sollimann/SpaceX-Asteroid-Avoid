package com.obstacleavoid.game.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.obstacleavoid.game.ObstacleAvoidGame
import com.obstacleavoid.game.config.GameConfig

fun main(arg: Array<String>) {
    val config = LwjglApplicationConfiguration()
    config.width = GameConfig.WIDTH
    config.height = GameConfig.HEIGHT
    LwjglApplication(ObstacleAvoidGame(), config)
}