package com.obstacleavoid.game.screen.game

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.Pools
import com.obstacleavoid.game.config.DifficultyLevel
import com.obstacleavoid.game.config.GameConfig
import com.obstacleavoid.game.entity.Obstacle
import com.obstacleavoid.game.entity.Player
import com.obstacleavoid.game.util.GdxArray
import com.obstacleavoid.game.util.isNotEmpty

class GameController {

    // private properties
    private val startPlayerX = GameConfig.WORLD_WIDTH / 2f
    private val startPlayerY = 1f
    private var obstacleTimer = 0f
    private var scoreTimer = 0f
    private var difficultyLevel = DifficultyLevel.EASY // enum setter


    // public properties
    val gameOver = false
        // boolean game over property using a getter
        // everytime we cal the gameOver val, it will execute
        // and return the result of lives <= 0
        //get() = lives <= 0

    val obstacles = GdxArray<Obstacle>()

    var player = Player()
        private set // can only set player inside GameController class
    var lives = GameConfig.LIVES_START
        private set // can only set player inside GameController class
    var score = 0
        private set
    var displayScore = 0

    private val obstaclePool = Pools.get(Obstacle::class.java, 20)


    // init
    init {
        // could have replaced this init block with:
        // var player = Player().apply { setPosition(startPlayerX, startPlayerY) }
        //     private set

        // position player
        player.setPosition(startPlayerX, startPlayerY)
        player.setSize(Player.SIZE, Player.SIZE)

    }

    // public functions
    fun update(delta: Float) {

        // if game is over, do not run the code below
        // jump out
        if(gameOver){
            return
        }

        // update game world
        player.update()
        blockPlayerFromLeavingWorldBounds()

        createNewObstacle(delta)
        updateObstacles()
        removePassedObstacles()
        updateScore(delta)
        updateDisplayScore(delta)

        if (isPlayerCollidingWithObstacle()) {
            lives--
        }
    }

    // private functions
    private fun updateScore(delta: Float) {
        scoreTimer += delta

        if (scoreTimer >= GameConfig.SCORE_MAX_TIME) {
            scoreTimer = 0f
            score += MathUtils.random(1, 5)
        }
    }

    private fun updateDisplayScore(delta: Float) {

        // condition tells us we have to update score in game UI
        if (displayScore < score) {
            displayScore = Math.min(score, displayScore + (60 * delta).toInt())
        }
    }

    private fun removePassedObstacles() {

        if (obstacles.isNotEmpty()) {
            val first = obstacles.first()
            val minObstacleY = -Obstacle.SIZE

            if (first.y < minObstacleY) {
                obstaclePool.free(first)
                obstacles.removeValue(first, true)
            }
        }
    }

    private fun isPlayerCollidingWithObstacle(): Boolean {
        obstacles.forEach {
            if (!it.hit && it.isCollidingWith(gameObject = player)) {
                return true
            }
        }

        return false
    }

    private fun updateObstacles() {
        obstacles.forEach { it.update() } // same as writing obstacle -> obstacle.update()
    }

    private fun createNewObstacle(delta: Float) {
        obstacleTimer += delta

        if (obstacleTimer >= GameConfig.OBSTACLE_SPAWN_TIME) {
            obstacleTimer = 0f // reset timer

            // spawn obstacle at random x position
            val obstacleX = MathUtils.random(0f, GameConfig.WORLD_WIDTH - Obstacle.SIZE)
            val obstacle = obstaclePool.obtain()
            obstacle.setPosition(obstacleX, GameConfig.WORLD_HEIGHT)
            obstacle.setSize(Obstacle.SIZE, Obstacle.SIZE)

            // set the obstacle speed
            obstacle.ySpeed = difficultyLevel.obstacleSpeed // enum getter

            // add to array
            obstacles.add(obstacle)
        }
    }

    private fun blockPlayerFromLeavingWorldBounds() {
        player.x = MathUtils.clamp(player.x, 0f, GameConfig.WORLD_WIDTH - Player.SIZE)
    }
}