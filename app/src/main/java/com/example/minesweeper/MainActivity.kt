package com.example.minesweeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), OnTileClicked {
    private lateinit var gridRecyclerView: RecyclerView
    private lateinit var mineGridRecyclerAdapter: MineGridRecyclerAdapter
    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gridRecyclerView = findViewById(R.id.mainGrid)
        gridRecyclerView.layoutManager = GridLayoutManager(this,9)
        game = Game(9)
        mineGridRecyclerAdapter = MineGridRecyclerAdapter(game.grid.tiles, this)
        gridRecyclerView.adapter = mineGridRecyclerAdapter
        Log.v("chuj","Aaaa")

    }

    override fun onTileClick(tile: Tile) {
        Log.v("chuj","Bruh")
        Toast.makeText(applicationContext,"Dziala",Toast.LENGTH_SHORT).show()
    }


    fun onClick(view: View) {
        Toast.makeText(applicationContext,"Dziala",Toast.LENGTH_SHORT).show()
    }
}