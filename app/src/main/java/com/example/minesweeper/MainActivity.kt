package com.example.minesweeper

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), OnTileClicked {
    private lateinit var gridRecyclerView: RecyclerView
    private lateinit var mineGridRecyclerAdapter: MineGridRecyclerAdapter
    private lateinit var game: Game
    private lateinit var smile: TextView
    private lateinit var flag: TextView
    private lateinit var flagCount: TextView
    private lateinit var timer: TextView
    private lateinit var countDownTimer: CountDownTimer
    private var seconds = 0
    private var timerStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        smile = findViewById(R.id.smile)
        smile.setOnClickListener {
            game = Game(game.size, game.numberOfBombs)
            mineGridRecyclerAdapter.setTilesB(game.grid.tiles)
            timerStarted = false
            countDownTimer.cancel()
            seconds = 0
            timer.setText(R.string.defaultCount)
            smile.text = this.resources.getString(R.string.smile)
            flagCount.text = String.format("%03d", game.numberOfBombs - game.flagCount)
        }


        timer = findViewById(R.id.timer)
        countDownTimer = object: CountDownTimer(999000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                seconds++
                timer.text = String.format("%03d", seconds)
            }

            override fun onFinish() {
                game.outOftime()
                Toast.makeText(applicationContext,"Game Over!", Toast.LENGTH_SHORT).show()
                smile.text = resources.getString(R.string.gameOver)
                game.grid.revealBombs()
                mineGridRecyclerAdapter.setTilesB(game.grid.tiles)
            }
        }

        gridRecyclerView = findViewById(R.id.mainGrid)
        gridRecyclerView.layoutManager = GridLayoutManager(this,9)
        game = Game(9, 12)
        mineGridRecyclerAdapter = MineGridRecyclerAdapter(game.grid.tiles, this)
        gridRecyclerView.adapter = mineGridRecyclerAdapter

        flag = findViewById(R.id.flag)
        flagCount = findViewById(R.id.flagsLeft)
        flagCount.text = String.format("%03d", game.numberOfBombs - game.flagCount)
        flag.setOnClickListener {
            game.changeMode()
        }
    }

    override fun onTileClick(tile: Tile) {
        game.onTileClicked(tile)
        flagCount.text = String.format("%03d", game.numberOfBombs - game.flagCount)
        if (!timerStarted) {
            countDownTimer.start()
            timerStarted = true
        }
        if (game.isGameOver) {
            Toast.makeText(applicationContext,"Game Over!", Toast.LENGTH_SHORT).show()
            game.grid.revealBombs()
            smile.text = resources.getString(R.string.gameOver)
            countDownTimer.cancel()
        }
        if (game.isGameWon()) {
            Toast.makeText(applicationContext,"You win!", Toast.LENGTH_SHORT).show()
            game.grid.revealBombs()
            smile.text = resources.getString(R.string.gameWon)
            countDownTimer.cancel()
        }
        mineGridRecyclerAdapter.setTilesB(game.grid.tiles)
    }
}