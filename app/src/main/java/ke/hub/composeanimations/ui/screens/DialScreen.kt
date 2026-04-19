package ke.hub.composeanimations.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun DialScreen(modifier: Modifier = Modifier) {
    var degree by remember { mutableFloatStateOf(90f) }
    val animatedDegree by animateFloatAsState(targetValue = degree)
    val colorScheme = MaterialTheme.colorScheme
    val animatable = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        animatable.animateTo(
            targetValue = 360f,
            animationSpec = tween(durationMillis = 2000),
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Canvas(
                modifier =
                    Modifier
                        .size(100.dp)
                        .graphicsLayer(scaleX = animatable.value, scaleY = animatable.value),
            ) {
                drawArc(
                    color = colorScheme.primary,
                    startAngle = 270f,
                    sweepAngle = 360f * (animatedDegree / 360f),
                    useCenter = false,
                    style =
                        Stroke(
                            width = 10f,
                            cap = StrokeCap.Round,
                        ),
                )
            }
            Canvas(modifier = Modifier.size(100.dp)) {
                drawCircle(
                    color = colorScheme.primary,
                    radius = 100f,
                    center = center,
                )
            }
            Text(
                text = "Degree: ${degree.toInt()}",
                color = colorScheme.primary,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }

//    Dial(
//        degree = animatedDegree,
//        onDegreeChange = { degree = it },
//        sweepDegrees = 330f,
//        startDegrees = 30f,
//        interval = 30f,
//        modifier = Modifier.size(280.dp),
//        valueRange = 1f..12f,
//    )
}

// @Composable
// fun Dial(
//    modifier: Modifier = Modifier,
//    degree: Float,
//    onDegreeChange: (Float) -> Unit,
//    sweepDegrees: Float,
//    startDegrees: Float,
//    interval: Float,
//    valueRange: ClosedFloatingPointRange<Float>,
// ) {
// }
