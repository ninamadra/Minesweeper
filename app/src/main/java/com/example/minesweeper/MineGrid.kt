package com.example.minesweeper

class MineGrid(size: Int) {
     var tiles = ArrayList<Tile>()

    init {
        for (i in 0 until size*size)
            tiles.add(Tile(State.EMPTY))
    }
}