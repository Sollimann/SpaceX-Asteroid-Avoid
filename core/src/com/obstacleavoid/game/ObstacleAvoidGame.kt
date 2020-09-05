package com.obstacleavoid.game

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.obstacleavoid.game.screen.game.GameScreen

class ObstacleAvoidGame : Game() {
    var batch: SpriteBatch? = null
    var img: Texture? = null

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        setScreen(GameScreen())
    }
}