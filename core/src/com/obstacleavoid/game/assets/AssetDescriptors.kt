package com.obstacleavoid.game.assets

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.obstacleavoid.game.util.assetDescriptor

object AssetDescriptors {
    val FONT = assetDescriptor<BitmapFont>(AssetPaths.PURSIA_FONT)
    val GAMEPLAY = assetDescriptor<TextureAtlas>(AssetPaths.GAMEPLAY)
}