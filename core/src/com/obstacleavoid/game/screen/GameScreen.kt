package com.obstacleavoid.game.screen

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.obstacleavoid.game.assets.AssetPaths
import com.obstacleavoid.game.config.GameConfig
import com.obstacleavoid.game.entity.Obstacle
import com.obstacleavoid.game.entity.Player
import com.obstacleavoid.game.util.GdxArray
import com.obstacleavoid.game.util.clearScreen
import com.obstacleavoid.game.util.debug.DebugCameraController
import com.obstacleavoid.game.util.drawGrid
import com.obstacleavoid.game.util.toInternalFile
import com.obstacleavoid.game.util.use

class GameScreen : Screen {

    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var uiCamera: OrthographicCamera
    private lateinit var uiViewport: Viewport
    private lateinit var renderer: ShapeRenderer
    private lateinit var player: Player
    private lateinit var debugCameraController: DebugCameraController
    private lateinit var batch: SpriteBatch
    private lateinit var uiFont: BitmapFont

    private var obstacleTimer = 0f
    private var scoreTimer = 0f
    private var score = 0
    private var displayScore = 0
    private var lives = GameConfig.LIVES_START
    private val padding = 20f

    private val obstacles = GdxArray<Obstacle>()
    private val layout = GlyphLayout()

    // boolean game over property using a getter
    // everytime we cal the gameOver val, it will execute
    // and return the result of lives <= 0
    private val gameOver
        get() = lives <= 0

    override fun show() {
        camera = OrthographicCamera()
        viewport = FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera)

        uiCamera = OrthographicCamera()
        uiViewport = FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT, uiCamera)

        renderer = ShapeRenderer()

        debugCameraController = DebugCameraController()
        debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y)

        // instantiate font
        batch = SpriteBatch()
        uiFont = BitmapFont(AssetPaths.PURSIA_FONT.toInternalFile())

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

        if (!gameOver) update(delta)

        clearScreen()
        renderer.projectionMatrix = camera.combined

        renderer.use {
            player.drawDebug(renderer)

            obstacles.forEach { it.drawDebug(renderer) }
        }

        renderUi()

        viewport.drawGrid(renderer)
    }

    private fun renderUi() {

        batch.projectionMatrix = uiCamera.combined

        batch.use {

            // draw lives
            val livesText = "LIVES: $lives"
            layout.setText(uiFont, livesText)
            uiFont.draw(batch, layout, 20f, GameConfig.HUD_HEIGHT - layout.height)

            // draw score
            val scoreText = "SCORE: " + displayScore
            layout.setText(uiFont, scoreText)
            uiFont.draw(batch, layout, GameConfig.HUD_WIDTH - layout.width - padding,
                    GameConfig.HUD_HEIGHT - layout.height)
        }
    }

    private fun update(delta: Float) {
        // update game world
        player.update()
        blockPlayerFromLeavingWorldBounds()

        updateObstacles()
        createNewObstacle(delta)
        updateScore(delta)
        updateDisplayScore(delta)

        if (isPlayerCollidingWithObstacle()) {
            lives--
        }
    }

    private fun updateScore(delta: Float) {
        scoreTimer += delta

        if (scoreTimer >= GameConfig.SCORE_MAX_TIME) {
            scoreTimer = 0f
            score += MathUtils.random(1, 5)
        }
    }

    private fun updateDisplayScore(delta: Float) {

        // condition tells us we have to update score in game UI
        if (displayScore < score) {
            displayScore = Math.min(score, displayScore + (60 * delta).toInt())
        }
    }

    private fun isPlayerCollidingWithObstacle(): Boolean {
        obstacles.forEach {
            if (!it.hit && it.isCollidingWith(gameObject = player)) {
                return true
            }
        }

        return false
    }

    private fun updateObstacles() {
        obstacles.forEach { it.update() } // same as writing obstacle -> obstacle.update()
    }

    private fun createNewObstacle(delta: Float) {
        obstacleTimer += delta

        if (obstacleTimer >= GameConfig.OBSTACLE_SPAWN_TIME) {
            obstacleTimer = 0f // reset timer

            // spawn obstacle at random x position
            val obstacleX = MathUtils.random(0f, GameConfig.WORLD_WIDTH)
            val obstacle = Obstacle()
            obstacle.setPosition(obstacleX, GameConfig.WORLD_HEIGHT)

            // add to array
            obstacles.add(obstacle)
        }
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
        uiViewport.update(width, height, true)
    }

    override fun dispose() {
        renderer.dispose()
        batch.dispose()
        uiFont.dispose()
    }

    override fun hide() {
        dispose()
    }
}