package com.napsis.gameoflife.ui.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.napsis.gameoflife.R
import com.napsis.gameoflife.data.Game

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    onStartPressed: (() -> Unit)? = null
) {
    val gameUiState by homeViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            gameUiState.boardState?.let { list ->
                list.forEachIndexed { _, item ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(id = R.drawable.carretera),
                            contentDescription = null
                        )
                        AnimatedContent(targetState = item,
                            transitionSpec = {
                                (slideInHorizontally { width -> -width } with
                                    slideOutHorizontally { width -> width })
                                    .using(
                                        // Disable clipping since the faded slide-in/out should
                                        // be displayed out of bounds.
                                        SizeTransform(clip = false)
                                    )
                            }, label = ""
                        ) { targetCount ->
                            if (targetCount == Game.CellType.EMPTY) {
                                // do nothing
                            } else {
                                val image = when (targetCount) {
                                    Game.CellType.TYPE_1 -> R.drawable.camion
                                    Game.CellType.TYPE_2 -> R.drawable.coche
                                    Game.CellType.TYPE_3 -> R.drawable.taxi
                                    Game.CellType.TYPE_4 -> R.drawable.autobus
                                    Game.CellType.TYPE_5 -> R.drawable.camioneta
                                    Game.CellType.TYPE_6 -> R.drawable.camioneta2
                                    else -> {
                                        R.drawable.camion
                                    }
                                }
                                Image(
                                    modifier = Modifier.fillMaxSize(),
                                    painter = painterResource(id = image),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(enabled = !gameUiState.isGameInProgress, onClick = { onStartPressed?.invoke() }) {
            Text(text = "Start")
        }
    }
}