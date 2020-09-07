package com.obstacleavoid.game.util

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion


inline operator fun TextureAtlas.get(regionName: String) : TextureRegion? = findRegion(regionName)