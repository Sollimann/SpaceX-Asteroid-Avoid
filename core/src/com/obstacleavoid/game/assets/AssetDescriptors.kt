package com.obstacleavoid.game.assets

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont

object AssetDescriptors {
    val FONT = AssetDescriptor<BitmapFont>(AssetPaths.PURSIA_FONT, BitmapFont::class.java)
    val BACKGROUND = AssetDescriptor<Texture>(AssetPaths.BACKGROUND_TEXTURE, Texture::class.java)
    val OBSTACLE = AssetDescriptor<Texture>(AssetPaths.OBSTACLE_TEXTURE, Texture::class.java)
    val PLAYER = AssetDescriptor<Texture>(AssetPaths.PLAYER_TEXTURE, Texture::class.java)
}