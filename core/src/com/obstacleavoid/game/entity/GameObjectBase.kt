package com.obstacleavoid.game.entity

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Intersector
import com.obstacleavoid.game.util.circle

abstract class GameObjectBase {
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

    abstract val bounds : Circle

    // public functions
    fun setPosition(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    fun drawDebug(renderer: ShapeRenderer){
        renderer.x(x,y,0.1f)
        renderer.circle(bounds)
    }

    open fun isCollidingWith(gameObject: GameObjectBase) = Intersector.overlaps(gameObject.bounds, bounds)

    // private functions
    private fun updateBounds() = bounds.setPosition(x,y)
}