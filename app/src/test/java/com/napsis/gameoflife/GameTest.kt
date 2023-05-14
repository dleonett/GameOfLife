package com.napsis.gameoflife

import com.napsis.gameoflife.data.Game
import org.junit.Test

import org.junit.Assert.*

class GameTest {

    @Test
    fun testGameInitializationBoardSize() {
        // Create a new Game object with size 10 and initial population of 5
        val game = Game(10, 5)

        // Verify that the size of the board is 10
        assertEquals(10, game.boardState.size)
    }

    @Test
    fun testGameInitializationOccupiedPositions() {
        // Create a new Game object with size 10 and initial population of 5
        val game = Game(10, 5)

        // Verify that the number of ones on the board is 5
        assertEquals(5, game.boardState.count { it == 1 })
    }

    @Test
    fun testGameInitializationPopulationGreaterThanSize() {
        // Create a new Game object with size 10 and initial population of 11
        val game = Game(10, 11)

        // Verify that the number of ones on the board is 5
        assertEquals(10, game.boardState.count { it == 1 })
    }

    @Test
    fun testGameNextStep() {
        // Create a new Game object with size 10 and initial population of 5
        val game = Game(10, 5)

        // Invoke auto play logic
        game.autoPlay()

        // Verify that the number of ones on the board is 0
        assertEquals(0, game.boardState.count { it == 1 })
    }

    @Test
    fun testGameBoardProvided() {
        // Create a new Game object from an existing board
        val boardState = mutableListOf(0, 0, 0, 1, 1, 1)
        val game = Game(boardState)

        // Verify that the number of ones on the board is 3
        assertEquals(3, game.boardState.count { it == 1 })

        // Invoke next step logic
        game.nextStep()

        // Verify that the number of ones on the board is 2
        assertEquals(2, game.boardState.count { it == 1 })
    }
}