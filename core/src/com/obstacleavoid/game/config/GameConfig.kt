package com.obstacleavoid.game.config

object GameConfig {

    const val WIDTH = 480 // pixels - desktop only
    const val HEIGHT = 800 // pixels - desktop only

    const val WORLD_WIDTH = 6.0f // world units
    const val WORLD_HEIGHT = 10.0f // world units

    const val HUD_WIDTH = 480f // world units, 1 : 1 ppu
    const val HUD_HEIGHT = 800f // world units, 1 : 1 ppu

    // game center
    const val WORLD_CENTER_X = WORLD_WIDTH / 2f
    const val WORLD_CENTER_Y = WORLD_HEIGHT / 2f

    // how fast obstacle spawn from the top
    const val OBSTACLE_SPAWN_TIME = 0.35f

    // player lives at start
    const val LIVES_START = 3

    // player update score rate
    const val SCORE_MAX_TIME = 1.25f

    // game difficulty
    const val EASY_OBSTACLE_SPEED = 0.1f
    const val MEDIUM_OBSTACLE_SPEED = 0.15f
    const val HARD_OBSTACLE_SPEED = 0.18f
}