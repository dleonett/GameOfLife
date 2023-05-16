package com.napsis.gameoflife.data

import kotlin.random.Random

class Game {
    private val size: Int
    private var population: Int
    var boardState: MutableList<CellType>

    constructor(boardStateParam: MutableList<CellType>) {
        boardState = boardStateParam
        population = boardState.count { it != CellType.EMPTY }
        size = boardState.size

        println(boardState)
    }

    constructor(sizeParam: Int, populationParam: Int) {
        size = sizeParam
        population = populationParam
        boardState = MutableList(size) { CellType.EMPTY }

        if (size >= 0 && population >= 0) {
            if (population > size) {
                population = size
            }
            when (population) {
                0 -> {
                    /*do nothing*/
                }

                else -> {
                    repeat(population) {
                        var isValidPosition = false
                        while (!isValidPosition) {
                            val position = Random.nextInt(0, size - 1)
                            if (boardState[position] == CellType.EMPTY) {
                                boardState[position] = nonEmptyCellTypes.random()
                                isValidPosition = true
                            }
                        }
                    }
                }
            }

            println(boardState)
        }
    }

    fun nextStep(): Boolean {
        var wasPositionUpdated = false
        repeat(size) { position ->
            if (!wasPositionUpdated) {
                if (boardState[position] != CellType.EMPTY) {
                    if (position + 1 < size) {
                        if (boardState[position + 1] == CellType.EMPTY) {
                            boardState[position + 1] = boardState[position]
                            boardState[position] = CellType.EMPTY
                            wasPositionUpdated = true
                        }
                    } else {
                        boardState[position] = CellType.EMPTY
                    }
                }
            } else {
                wasPositionUpdated = false
            }
        }
        println(boardState)

        return isGameOver()
    }

    fun autoPlay() {
        while (!isGameOver()) {
            nextStep()
        }
    }

    fun isGameOver() = boardState.count { it == CellType.EMPTY } == size

    enum class CellType { EMPTY, TYPE_1, TYPE_2, TYPE_3, TYPE_4, TYPE_5, TYPE_6 }

    companion object {
        val nonEmptyCellTypes = listOf(
            CellType.TYPE_1,
            CellType.TYPE_2,
            CellType.TYPE_3,
            CellType.TYPE_4,
            CellType.TYPE_5,
            CellType.TYPE_6
        )
    }

}