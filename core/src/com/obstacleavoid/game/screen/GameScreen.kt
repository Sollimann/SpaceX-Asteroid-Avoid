package com.obstacleavoid.game.screen

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.obstacleavoid.game.util.clearScreen
import com.obstacleavoid.game.util.toInternalFile
import com.obstacleavoid.game.util.use

class GameScreen : Screen {

    private lateinit var batch:SpriteBatch
    private lateinit var img: Texture

    override fun hide() {
        dispose()
    }

    override fun show() {
        batch = SpriteBatch()
        img = Texture("badlogic.jpg".toInternalFile())
    }

    override fun render(delta: Float) {
        clearScreen()

        batch.use { batch.draw(img, 0f, 0f) }
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun resize(width: Int, height: Int) {

    }

    override fun dispose() {
        batch.dispose()
        img.dispose()
    }

}