package com.example.minesweeper

class Tile(private var value: State) {

    private var isRevealed = false
    private var isFlagged = false

    }
enum class State {
    BOMB,
    EMPTY,

}