package com.example.minesweeper

class Game(var size: Int, var numberOfBombs: Int) {
     public val grid: MineGrid = MineGrid(size)
     init {
          grid.generateGrid(numberOfBombs)
     }
}