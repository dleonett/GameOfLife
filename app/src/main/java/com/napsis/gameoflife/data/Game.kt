package com.napsis.gameoflife.data

import kotlin.random.Random

class Game {
    private val size: Int
    private var population: Int
    var boardState: MutableList<Int>

    constructor(boardStateParam: MutableList<Int>) {
        boardState = boardStateParam
        population = boardState.count { it == 1 }
        size = boardState.size

        println(boardState)
    }

    constructor(sizeParam: Int, populationParam: Int) {
        size = sizeParam
        population = populationParam
        boardState = MutableList(size) { 0 }

        if (size >= 0 && population >= 0) {
            if (population > size) {
                population = size
            }
            when (population) {
                0 -> {
                    /*do nothing*/
                }

                size -> {
                    boardState = MutableList(size) { 1 }
                }

                else -> {
                    repeat(population) {
                        var isValidPosition = false
                        while (!isValidPosition) {
                            val position = Random.nextInt(0, size - 1)
                            if (boardState[position] == 0) {
                                boardState[position] = 1
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
                if (boardState[position] == 1) {
                    if (position + 1 < size) {
                        if (boardState[position + 1] == 0) {
                            boardState[position + 1] = 1
                            boardState[position] = 0
                            wasPositionUpdated = true
                        }
                    } else {
                        boardState[position] = 0
                    }
                }
            } else {
                wasPositionUpdated = false
            }
        }
        println(boardState)

        return boardState.count { it == 1 } == 0
    }

    fun autoPlay() {
        while (boardState.count { it == 1 } > 0) {
            nextStep()
        }
    }

}