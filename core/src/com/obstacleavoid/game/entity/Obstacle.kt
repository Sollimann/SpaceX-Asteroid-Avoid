package com.obstacleavoid.game.entity

import com.badlogic.gdx.math.Circle
import com.obstacleavoid.game.util.logger

class Obstacle {
    companion object {

        // constants
        const val BOUNDS_RADIUS = 0.4f
        const val SIZE = 2 * BOUNDS_RADIUS
    }

    // public properties
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

    val bounds = Circle(x, y, BOUNDS_RADIUS)

    // private properties
    private var ySpeed = 0.1f

    // public functions
    fun update() {
        y -= ySpeed
    }

    fun setPosition(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    // private methods
    private fun updateBounds() {
        bounds.setPosition(x, y)
    }
}