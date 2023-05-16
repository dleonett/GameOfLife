package com.napsis.gameoflife.ui.home

import com.napsis.gameoflife.data.Game

data class HomeUiState(
    val boardState: List<Game.CellType>? = null,
    val isGameInProgress: Boolean = false,
    val reset: Boolean = false
)