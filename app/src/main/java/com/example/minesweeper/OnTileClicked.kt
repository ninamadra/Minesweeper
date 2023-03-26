package com.example.minesweeper

interface OnTileClicked {
    fun onTileClick(tile: Tile)
    fun onTileLongClick(tile: Tile)
}