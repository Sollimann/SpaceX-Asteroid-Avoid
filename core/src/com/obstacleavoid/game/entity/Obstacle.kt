package com.obstacleavoid.game.entity

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Intersector

class Obstacle : GameObjectBase() {
    companion object {

        // constants
        private const val BOUNDS_RADIUS = 0.3f
    }

    var hit = false
    var ySpeed = 0.1f

    // public functions
    fun update() {
        y -= ySpeed
    }

    override val bounds: Circle = Circle(x,y, BOUNDS_RADIUS)

    override fun isCollidingWith(gameObject: GameObjectBase): Boolean {
        val overlaps = super.isCollidingWith(gameObject)
        hit = overlaps
        return overlaps
    }
}