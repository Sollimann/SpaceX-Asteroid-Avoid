package com.obstacleavoid.game.screen

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.obstacleavoid.game.config.GameConfig
import com.obstacleavoid.game.entity.Player
import com.obstacleavoid.game.util.clearScreen
import com.obstacleavoid.game.util.debug.DebugCameraController
import com.obstacleavoid.game.util.drawGrid
import com.obstacleavoid.game.util.use

class GameScreen : Screen {

    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var renderer: ShapeRenderer
    private lateinit var player: Player
    private lateinit var debugCameraController: DebugCameraController

    override fun hide() {
        dispose()
    }

    override fun show() {
        camera = OrthographicCamera()
        viewport = FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera)
        renderer = ShapeRenderer()

        debugCameraController = DebugCameraController()
        debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y)

        // create player
        player = Player()

        // calculate position
        val startPlayerX = GameConfig.WORLD_WIDTH / 2f

        // position player
        player.setPosition(startPlayerX, 1f)

    }

    override fun render(delta: Float) {
        // handle debug camera controller
        debugCameraController.handleDebugInput()
        debugCameraController.applyTo(camera)

        // update game world
        player.update()
        blockPlayerFromLeavingWorldBounds()


        clearScreen()
        renderer.projectionMatrix = camera.combined

        renderer.use { player.drawDebug(renderer)}

        viewport.drawGrid(renderer)
    }

    private fun blockPlayerFromLeavingWorldBounds() {
        /*
        if(player.x < Player.HALF_SIZE){
            player.x = Player.BOUNDS_RADIUS
        }

        if(player.x > GameConfig.WORLD_WIDTH - Player.HALF_SIZE){
            player.x = GameConfig.WORLD_WIDTH - Player.HALF_SIZE
        }
         */

        player.x = MathUtils.clamp(player.x, Player.HALF_SIZE, GameConfig.WORLD_WIDTH - Player.HALF_SIZE)
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