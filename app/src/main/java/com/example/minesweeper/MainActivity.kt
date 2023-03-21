package com.example.minesweeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), OnTileClicked {
    private lateinit var gridRecyclerView: RecyclerView
    private lateinit var mineGridRecyclerAdapter: MineGridRecyclerAdapter
    private lateinit var game: Game
    private lateinit var smile: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        smile = findViewById(R.id.smile)
        smile.setOnClickListener {
            game = Game(game.size, game.numberOfBombs)
            mineGridRecyclerAdapter.setTilesB(game.grid.tiles)
        }
        gridRecyclerView = findViewById(R.id.mainGrid)
        gridRecyclerView.layoutManager = GridLayoutManager(this,9)
        game = Game(9, 30)
        mineGridRecyclerAdapter = MineGridRecyclerAdapter(game.grid.tiles, this)
        gridRecyclerView.adapter = mineGridRecyclerAdapter
    }

    override fun onTileClick(tile: Tile) {

        Toast.makeText(applicationContext,"Dziala",Toast.LENGTH_SHORT).show()
    }

    fun onClick(view: View) {
        Toast.makeText(applicationContext,"Dziala",Toast.LENGTH_SHORT).show()
    }
}