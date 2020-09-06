package com.obstacleavoid.game.entity

import com.badlogic.gdx.math.Circle

class Obstacle : GameObjectBase() {
    companion object {

        // constants
        const val SIZE = 0.6f
        const val HALF_SIZE = SIZE / 2f
    }

    var hit = false
    var ySpeed = 0.1f

    // public functions
    fun update() {
        y -= ySpeed
    }

    override val bounds: Circle = Circle(x,y, HALF_SIZE)

    override fun isCollidingWith(gameObject: GameObjectBase): Boolean {
        val overlaps = super.isCollidingWith(gameObject)
        hit = overlaps
        return overlaps
    }
}