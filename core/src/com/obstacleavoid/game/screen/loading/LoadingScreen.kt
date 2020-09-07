package com.obstacleavoid.game.screen.loading

import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.obstacleavoid.game.ObstacleAvoidGame
import com.obstacleavoid.game.assets.AssetDescriptors
import com.obstacleavoid.game.config.GameConfig
import com.obstacleavoid.game.screen.game.GameScreen
import com.obstacleavoid.game.util.clearScreen
import com.obstacleavoid.game.util.logger
import com.obstacleavoid.game.util.use

class LoadingScreen(private val game: ObstacleAvoidGame) : ScreenAdapter() {

    companion object {
        @JvmStatic
        private val log = logger<LoadingScreen>()

        private const val PROGRESS_BAR_WIDTH = GameConfig.HUD_WIDTH / 2f
        private const val PROGRESS_BAR_HEIGHT = 60f // world units
    }

    private val assetManager = game.assetManager
    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var renderer : ShapeRenderer

    private var progress = 0f
    private var waitTime = 0.75f
    private var changeScreen = false

    override fun show() {
        camera = OrthographicCamera()
        viewport = FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT, camera)
        renderer = ShapeRenderer()

        assetManager.load(AssetDescriptors.FONT)
        assetManager.load(AssetDescriptors.GAMEPLAY)

    }

    override fun render(delta: Float) {
        update(delta)

        clearScreen()
        viewport.apply()
        renderer.projectionMatrix = camera.combined

        renderer.begin(ShapeRenderer.ShapeType.Filled)
        draw()
        renderer.end()

        if(changeScreen) {
            game.screen = GameScreen(game)
        }
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun hide() {
        dispose()
    }

    override fun dispose() {
        log.debug("dispose")
        renderer.dispose()
    }

    // private functions
    private fun update(delta: Float) {
        // progress is between 0 and 1
        progress = assetManager.progress

        // update returns true when all assets are loaded
        if(assetManager.update()) {
            waitTime -= delta

            if(waitTime <= 0) {
                changeScreen = true
            }
        }
    }

    // ensures that progress bar is at center of screen
    private fun draw() {
        val progressBarX = (GameConfig.HUD_WIDTH - PROGRESS_BAR_WIDTH) / 2f
        val progressBarY = (GameConfig.HUD_HEIGHT - PROGRESS_BAR_HEIGHT) / 2f

        renderer.rect(progressBarX, progressBarY, progress * PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT)
    }
}