package com.example.minesweeper

import android.util.Log
import kotlin.random.Random

class MineGrid(private val size: Int) {
     var tiles = ArrayList<Tile>()

    init {
        for (i in 0 until size*size)
            tiles.add(Tile(State.EMPTY))
    }

    fun generateGrid(numberOfBombs: Int) {
        var counter = 0
        while ( counter < numberOfBombs) {
            val x = Random.nextInt(size)
            val y = Random.nextInt(size)
            val index = toIndex(x,y)
            if(tiles[index].state == State.EMPTY) {
                tiles[index].state = State.BOMB
                counter++
            }
        }

        for ( x in 0 until size) {
            for ( y in 0 until size) {
                if (tileAt(x,y)?.state != State.BOMB) {
                    val adjacentTiles = adjacentTiles(x,y)
                    var bombCounter = 0
                    for(tile in adjacentTiles) {
                        if (tile.state == State.BOMB) {
                            bombCounter++
                        }
                    }
                    if (bombCounter > 0) {
                        tiles[x + (y * size)] = Tile(bombCounter)
                    }
                }
            }
        }
    }

    fun toIndex(x: Int, y:Int): Int {
        return x + y * size
    }

    fun tileAt(x: Int, y: Int): Tile? {
        if(x < 0 || x >= size || y < 0 || y >= size) {
            return null
        }
        return tiles[toIndex(x,y)]
    }

    fun adjacentTiles(x: Int, y: Int): ArrayList<Tile> {
        val arrayList = ArrayList<Tile?>()
        arrayList.add(tileAt(x-1,y))
        arrayList.add(tileAt(x+1,y))
        arrayList.add(tileAt(x-1,y-1))
        arrayList.add(tileAt(x,y-1))
        arrayList.add(tileAt(x+1,y-1))
        arrayList.add(tileAt(x-1,y+1))
        arrayList.add(tileAt(x,y+1))
        arrayList.add(tileAt(x+1,y+1))
        return arrayList.filterNotNull().toCollection(ArrayList<Tile>())

    }

    fun toXY(index: Int) : ArrayList<Int> {
        val y = index / size
        val x = index - y * size
        return  intArrayOf(x,y).toCollection(ArrayList())
    }
}