package com.example.minesweeper

class Game(var size: Int, var numberOfBombs: Int) {
    val grid: MineGrid = MineGrid(size)
    private var clearMode = true
    private var flagMode = false
    var isGameOver = false
    private var timeExpired = false
    var flagCount = 0

    init {
          grid.generateGrid(numberOfBombs)
    }

    fun onTileClicked(tile: Tile) {
        if (!isGameOver && !isGameWon() &&!timeExpired) {
            if(clearMode) {
                clear(tile)
            }
        }
        if (flagMode) {
            flag(tile)
        }
    }

    private fun clear(tile: Tile) {
        val index = grid.tiles.indexOf(tile)
        grid.tiles[index].isRevealed = true

        if(tile.state == State.EMPTY) {
            val toClear = ArrayList<Tile>()
            val toCheckAdjacents = ArrayList<Tile>()
            toCheckAdjacents.add(tile)

            while(toCheckAdjacents.isNotEmpty()) {
                val toCheck = toCheckAdjacents[0]
                val tileIndex = grid.tiles.indexOf(toCheck)
                val tilePosition = grid.toXY(tileIndex)

                for (adjacent in grid.adjacentTiles(tilePosition[0],tilePosition[1])) {
                    if (adjacent.state == State.EMPTY) {
                        if (!toClear.contains(adjacent)) {
                            if (!toCheckAdjacents.contains(adjacent)) {
                                toCheckAdjacents.add(adjacent)
                            }

                        }
                    }
                    else if(!toClear.contains(adjacent)) {
                            toClear.add(adjacent)
                    }
                }
                toCheckAdjacents.remove(toCheck)
                toClear.add(toCheck)

            }
            for (t in toClear) {
                t.isRevealed = true
            }

        }
        else if (tile.state == State.BOMB) {
            isGameOver = true
        }
    }

     fun isGameWon(): Boolean {
        var tilesUnrevealed = 0
        for (tile in grid.tiles) {
            if (tile.state == State.NEIGHBOR && !tile.isRevealed ) {
                tilesUnrevealed++
            }
        }
        if (tilesUnrevealed == 0) {
            return true
        }
        return false
    }

    fun outOftime() {
        timeExpired = true
    }

    private fun flag(tile: Tile) {
        println("flagging")
        if (!tile.isRevealed) {
            tile.isFlagged = !tile.isFlagged
            var count = 0
            for(t in grid.tiles) {
                if (t.isFlagged) {
                    count++
                }
            }
            flagCount = count
        }
    }

    fun changeMode() {
        clearMode = !clearMode
        flagMode = !flagMode
    }
}