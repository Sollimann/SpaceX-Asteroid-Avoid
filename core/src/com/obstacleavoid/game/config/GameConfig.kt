package com.obstacleavoid.game.config

object GameConfig {

    const val WIDTH = 480 // pixels - desktop only
    const val HEIGHT = 800 // pixels - desktop only

    const val WORLD_WIDTH = 6.0f // world units
    const val WORLD_HEIGHT = 10.0f // world units

    const val HUD_WIDTH = 480f // world units, 1 : 1 ppu
    const val HUD_HEIGHT = 800f // world units, 1 : 1 ppu

    const val WORLD_CENTER_X = WORLD_WIDTH / 2f
    const val WORLD_CENTER_Y = WORLD_HEIGHT / 2f

    const val OBSTACLE_SPAWN_TIME = 0.35f
    const val LIVES_START = 3
}