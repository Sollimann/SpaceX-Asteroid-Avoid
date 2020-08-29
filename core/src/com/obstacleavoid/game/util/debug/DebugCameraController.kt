package com.obstacleavoid.game.util.debug

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.obstacleavoid.game.util.isKeyPressed
import com.obstacleavoid.game.util.logger

class DebugCameraController {

    companion object {
        @JvmStatic
        private val log = logger<DebugCameraController>()

        private const val DEFAULT_LEFT_KEY = Input.Keys.A
        private const val DEFAULT_RIGHT_KEY = Input.Keys.D
        private const val DEFAULT_UP_KEY = Input.Keys.W
        private const val DEFAULT_DOWN_KEY = Input.Keys.S
        private const val DEFAULT_ZOOM_IN_KEY = Input.Keys.COMMA
        private const val DEFAULT_ZOOM_OUT_KEY = Input.Keys.PERIOD
        private const val DEFAULT_RESET_KEY = Input.Keys.BACKSPACE
        private const val DEFAULT_LOG_KEY = Input.Keys.ENTER
        private const val DEFAULT_MOVE_SPEED = 20f
        private const val DEFAULT_ZOOM_SPEED = 2f
        private const val DEFAULT_MAX_ZOOM_IN = 0.25f
        private const val DEFAULT_MAX_ZOOM_OUT = 10f
    }

    // properties
    private val position = Vector2()
    private val startPosition = Vector2()

    private var zoom = 1f
        set(value) {
            // the setter will make sure that zoom is within bounds
            field = MathUtils.clamp(value, DEFAULT_MAX_ZOOM_IN, DEFAULT_MAX_ZOOM_OUT)
        }

    // public functions
    fun setStartPosition(x: Float, y: Float){
        startPosition.set(x,y)
        position.set(x,y)
    }

    fun applyTo(camera: OrthographicCamera){
        camera.position.set(position, 0f)
        camera.zoom = zoom
        camera.update()
    }

    fun handleDebugInput() {
        val delta = Gdx.graphics.deltaTime
        val moveSpeed = DEFAULT_MOVE_SPEED * delta
        val zoomSpeed = DEFAULT_ZOOM_SPEED * delta

        when {
            // move controls
            DEFAULT_LEFT_KEY.isKeyPressed() -> moveLeft(moveSpeed)
            DEFAULT_RIGHT_KEY.isKeyPressed() -> moveRight(moveSpeed)
            DEFAULT_UP_KEY.isKeyPressed() -> moveUp(moveSpeed)
            DEFAULT_DOWN_KEY.isKeyPressed() -> moveDown(moveSpeed)

            // zoom controls
            DEFAULT_ZOOM_IN_KEY.isKeyPressed() -> zoomIn(zoomSpeed)
            DEFAULT_ZOOM_OUT_KEY.isKeyPressed() -> zoomOut(zoomSpeed)

            // resest/log controls
            DEFAULT_RESET_KEY.isKeyPressed() -> reset()
            DEFAULT_LOG_KEY.isKeyPressed() -> log.debug("position= $position, zoom= $zoom")
        }
    }

    // private functions
    private fun moveCamera(xSpeed: Float, ySpeed: Float) = setPosition(position.x + xSpeed, position.y + ySpeed)
    private fun setPosition(x: Float, y: Float) = position.set(x,y)
    private fun moveLeft(speed: Float) = moveCamera(-speed, 0f)
    private fun moveRight(speed: Float) = moveCamera(speed, 0f)
    private fun moveUp(speed: Float) = moveCamera(0f, speed)
    private fun moveDown(speed: Float) = moveCamera(0f, -speed)

    private fun zoomIn(zoomSpeed: Float) {
        zoom += zoomSpeed
    }

    private fun zoomOut(zoomSpeed: Float) {
        zoom -= zoomSpeed
    }

    private fun reset() {
        position.set(startPosition)
        zoom = 1f
    }

}