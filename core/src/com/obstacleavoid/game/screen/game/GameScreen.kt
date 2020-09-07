package com.obstacleavoid.game.screen.game

import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetDescriptor
import com.obstacleavoid.game.ObstacleAvoidGame
import com.obstacleavoid.game.assets.AssetDescriptors
import com.obstacleavoid.game.util.logger

class GameScreen(val game : ObstacleAvoidGame) : Screen {

    companion object {
        @JvmStatic
        private val log = logger<GameScreen>()
    }

    private val assetManager = game.assetManager
    private lateinit var controller : GameController
    private lateinit var renderer : GameRenderer

    /** Called when this screen becomes the current screen for a {@link Game}. */
    override fun show() {
        assetManager.load(AssetDescriptors.FONT)
        assetManager.load(AssetDescriptors.GAMEPLAY)

        // blocks until all resources/assets are loaded
        assetManager.finishLoading()

        log.debug("assetMananger diagnostics= ${assetManager.diagnostics}")

        controller = GameController()
        renderer = GameRenderer(assetManager, controller)
    }

    /** Called when the screen should render itself.
     * @param delta The time in seconds since the last render. */
    override fun render(delta: Float) {
        controller.update(delta)
        renderer.render()
    }

    /** Called when the {@link Application} is paused, usually when it's not active or visible on-screen. An Application is also
     * paused before it is destroyed. */
    override fun pause() {

    }

    /** Called when the {@link Application} is resumed from a paused state, usually when it regains focus. */
    override fun resume() {

    }

    /** Called when the {@link Application} is resized. This can happen at any point during a non-paused state but will never happen
     * before a call to {@link #create()}.
     *
     * @param width the new width in pixels
     * @param height the new height in pixels */
    override fun resize(width: Int, height: Int) {
        renderer.resize(width, height)
    }

    /** Called when this screen should release all resources. */
    override fun dispose() {
        renderer.dispose()
    }
    /** Called when this screen is no longer the current screen for a {@link Game}. */
    override fun hide() {
        // WARNING: Screens are not disposed automatically
       dispose()
    }

}