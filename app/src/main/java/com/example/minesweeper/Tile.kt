package com.example.minesweeper

class Tile(var state: State) {
    constructor(value: Int) : this(State.NEIGHBOR) {
        this.value = value
    }

    var isRevealed = false
    var isFlagged = false
    var value: Int = 0

}
enum class State {
    BOMB,
    EMPTY,
    NEIGHBOR

}