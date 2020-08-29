package com.obstacleavoid.game.entity

import com.badlogic.gdx.Input.*
import com.badlogic.gdx.math.Circle
import com.obstacleavoid.game.util.isKeyPressed

class Player : GameObjectBase() {

    companion object{
        // constants
        private const val BOUNDS_RADIUS = 0.4f // world units
        private const val MAX_X_SPEED = 0.25f // world units

        const val HALF_SIZE = BOUNDS_RADIUS
    }

    override val bounds: Circle = Circle(x,y, BOUNDS_RADIUS)

    fun update() {
        var xSpeed = 0f

        when {
            Keys.RIGHT.isKeyPressed() -> xSpeed = MAX_X_SPEED
            Keys.LEFT.isKeyPressed() -> xSpeed = -MAX_X_SPEED
        }

        x += xSpeed
    }
}