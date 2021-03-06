package com.obstacleavoid.game.entity

import com.badlogic.gdx.Input.*
import com.badlogic.gdx.math.Circle
import com.obstacleavoid.game.util.isKeyPressed

class Player : GameObjectBase() {

    companion object{
        // constants
        const val SIZE = 0.8f // world units
        const val HALF_SIZE = SIZE / 2f // world units

        const val MAX_X_SPEED = 0.25f // world units
    }

    override val bounds: Circle = Circle(x,y, HALF_SIZE)
}