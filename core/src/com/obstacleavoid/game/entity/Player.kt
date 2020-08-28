package com.obstacleavoid.game.entity

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Circle

class Player {

    companion object{
        // constants
        private const val BOUNDS_RADIUS = 0.4f // world units
        private const val SIZE = BOUNDS_RADIUS * 2f
    }

    // properties
    var x: Float = 0f
    var y: Float = 0f

    val bounds: Circle

    // init
    init {
        bounds = Circle(x,y, BOUNDS_RADIUS)
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