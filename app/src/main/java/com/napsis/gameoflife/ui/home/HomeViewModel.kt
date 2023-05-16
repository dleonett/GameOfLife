package com.napsis.gameoflife.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.napsis.gameoflife.data.Game
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private lateinit var game: Game
    private val size = 10
    private val population = 8
    private val updateMillis = 600L

    init {
        _uiState.update {
            it.copy(
                boardState = MutableList(size) { Game.CellType.EMPTY },
                isGameInProgress = false,
                reset = true
            )
        }
    }

    private fun reset() {
        game = Game(size, population)

        _uiState.update {
            it.copy(
                boardState = game.boardState.toList(),
                isGameInProgress = false,
                reset = true
            )
        }
    }

    fun start() {
        reset()

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isGameInProgress = true,
                    reset = false
                )
            }

            delay(updateMillis)

            while (!game.isGameOver()) {
                game.nextStep()

                _uiState.update {
                    it.copy(
                        boardState = game.boardState.toList(),
                        reset = false
                    )
                }

                delay(updateMillis)
            }

            _uiState.update {
                it.copy(
                    isGameInProgress = false,
                    reset = false
                )
            }
        }
    }

}