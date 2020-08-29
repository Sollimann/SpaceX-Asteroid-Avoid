package com.obstacleavoid.game.entity

import com.badlogic.gdx.Input.*
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Circle
import com.obstacleavoid.game.util.isKeyPressed

class Player {

    companion object{
        // constants
        const val BOUNDS_RADIUS = 0.4f // world units
        const val HALF_SIZE = BOUNDS_RADIUS
        private const val SIZE = BOUNDS_RADIUS * 2f // world units
        private const val MAX_X_SPEED = 0.25f // world units
    }

    // properties
    var x: Float = 0f
        set(value) {
            field = value
            updateBounds()
        }

    var y: Float = 0f
        set(value) {
            field = value
            updateBounds()
        }

    val bounds = Circle(x,y, BOUNDS_RADIUS)

    fun update() {
        var xSpeed = 0f

        when {
            Keys.RIGHT.isKeyPressed() -> xSpeed = MAX_X_SPEED
            Keys.LEFT.isKeyPressed() -> xSpeed = -MAX_X_SPEED
        }

        x += xSpeed
    }

    // public functions
    fun setPosition(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    fun drawDebug(renderer: ShapeRenderer) = renderer.circle(bounds.x, bounds.y, bounds.radius, 30)

    // private functions
    private fun updateBounds() = bounds.setPosition(x,y)
}