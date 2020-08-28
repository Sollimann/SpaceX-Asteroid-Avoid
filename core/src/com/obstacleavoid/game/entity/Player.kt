package com.obstacleavoid.game.entity

import com.badlogic.gdx.Input.*
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Circle
import com.obstacleavoid.game.util.isKeyPressed

class Player {

    companion object{
        // constants
        private const val BOUNDS_RADIUS = 0.4f // world units
        private const val SIZE = BOUNDS_RADIUS * 2f // world units
        private const val MAX_X_SPEED = 0.25f // world units
    }

    // properties
    var x: Float = 0f
    var y: Float = 0f

    val bounds: Circle

    // init
    init {
        bounds = Circle(x,y, BOUNDS_RADIUS)
    }


    fun update() {
        var xSpeed = 0f

        when {
            Keys.RIGHT.isKeyPressed() -> xSpeed = MAX_X_SPEED
            Keys.LEFT.isKeyPressed() -> xSpeed = -MAX_X_SPEED
        }

        x += xSpeed
        updateBounds()
    }

    // public functions
    fun setPosition(x: Float, y: Float) {
        this.x = x
        this.y = y
        updateBounds()
    }

    fun drawDebug(renderer: ShapeRenderer) = renderer.circle(bounds.x, bounds.y, bounds.radius, 30)

    // private functions
    private fun updateBounds() = bounds.setPosition(x,y)
}