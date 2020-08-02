package com.obstacleavoid.game.screen

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.obstacleavoid.game.config.GameConfig
import com.obstacleavoid.game.util.clearScreen
import com.obstacleavoid.game.util.drawGrid
import com.obstacleavoid.game.util.toInternalFile
import com.obstacleavoid.game.util.use

class GameScreen : Screen {

    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var renderer: ShapeRenderer

    override fun hide() {
        dispose()
    }

    override fun show() {
        camera = OrthographicCamera()
        viewport = FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera)
        renderer = ShapeRenderer()
    }

    override fun render(delta: Float) {
        clearScreen()
        camera.zoom = 2f
        renderer.projectionMatrix = camera.combined

        viewport.drawGrid(renderer)
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun dispose() {
        renderer.dispose()
    }

}