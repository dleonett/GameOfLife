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

    init {
        reset()
    }

    private fun reset() {
        game = Game(15, 10)

        _uiState.update {
            it.copy(
                boardState = game.boardState.toList(),
                isGameInProgress = false
            )
        }
    }

    fun start() {
        reset()

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isGameInProgress = true
                )
            }

            while (!game.isGameOver()) {
                game.nextStep()

                _uiState.update {
                    it.copy(
                        boardState = game.boardState.toList()
                    )
                }

                delay(200)
            }

            _uiState.update {
                it.copy(
                    isGameInProgress = false
                )
            }
        }
    }

}