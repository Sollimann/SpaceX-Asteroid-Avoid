package com.obstacleavoid.game.assets

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.obstacleavoid.game.util.assetDescriptor

object AssetDescriptors {
    val FONT = assetDescriptor<BitmapFont>(AssetPaths.PURSIA_FONT)
    val BACKGROUND = assetDescriptor<Texture>(AssetPaths.BACKGROUND_TEXTURE)
    val OBSTACLE = assetDescriptor<Texture>(AssetPaths.OBSTACLE_TEXTURE)
    val PLAYER = assetDescriptor<Texture>(AssetPaths.PLAYER_TEXTURE)
}