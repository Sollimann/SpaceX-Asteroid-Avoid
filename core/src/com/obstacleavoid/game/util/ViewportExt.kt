package com.obstacleavoid.game.util

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.Viewport

// JvmOverloads decorator instructs the Kotlin compiler to generate
// overloads for this function that substitute default parameter values.
@JvmOverloads
fun Viewport.drawGrid(renderer: ShapeRenderer, cellSize: Int = 1){
    val oldColor = renderer.color.cpy()
    val doubleWorldWidth = worldWidth * 2
    val doubleWorldHeight = worldHeight * 2

    apply()
    renderer.use {
        renderer.color = Color.WHITE

        // draw vertical lines
        var x = -doubleWorldWidth
        while(x < doubleWorldWidth){
            renderer.line(x, -doubleWorldHeight, x, doubleWorldHeight)
            x += cellSize
        }

        // draw horizontal lines
        var y = -doubleWorldHeight
        while(y < doubleWorldHeight){
            renderer.line(-doubleWorldWidth, y, doubleWorldWidth, y)
            y += cellSize
        }

        // draw 0/0 lines
        renderer.color = Color.RED
        renderer.line(0f, -doubleWorldHeight, 0f, doubleWorldHeight)
        renderer.line(-doubleWorldWidth, 0f, doubleWorldWidth, 0f)

        // draw world bounds
        renderer.color = Color.GREEN
        renderer.line(0f, worldHeight, worldWidth, worldHeight)
        renderer.line(worldWidth, 0f, worldWidth, worldHeight)
    }
    renderer.color = oldColor
}