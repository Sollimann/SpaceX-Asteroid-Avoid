package com.obstacleavoid.game.util.debug

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
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
        private const val DEFAULT_MOVE_SPEED = 20f
    }

    // properties
    private val position = Vector2()
    private val startPosition = Vector2()

    // public functions
    fun setStartPosition(x: Float, y: Float){
        startPosition.set(x,y)
        position.set(x,y)
    }

    fun applyTo(camera: OrthographicCamera){
        camera.position.set(position, 0f)
        camera.update()
    }

    fun handleDebugInput() {
        val delta = Gdx.graphics.deltaTime
        val moveSpeed = DEFAULT_MOVE_SPEED * delta

        when {
            // move controls
            DEFAULT_LEFT_KEY.isKeyPressed() -> moveLeft(moveSpeed)
            DEFAULT_RIGHT_KEY.isKeyPressed() -> moveRight(moveSpeed)
            DEFAULT_UP_KEY.isKeyPressed() -> moveUp(moveSpeed)
            DEFAULT_DOWN_KEY.isKeyPressed() -> moveDown(moveSpeed)
        }
    }

    // private functions
    private fun moveCamera(xSpeed: Float, ySpeed: Float) = setPosition(position.x + xSpeed, position.y + ySpeed)
    private fun setPosition(x: Float, y: Float) = position.set(x,y)
    private fun moveLeft(speed: Float) = moveCamera(-speed, 0f)
    private fun moveRight(speed: Float) = moveCamera(speed, 0f)
    private fun moveUp(speed: Float) = moveCamera(0f, speed)
    private fun moveDown(speed: Float) = moveCamera(0f, -speed)

}