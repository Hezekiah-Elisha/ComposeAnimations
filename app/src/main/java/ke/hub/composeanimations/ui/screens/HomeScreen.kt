package ke.hub.composeanimations.ui.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { innerpadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerpadding)
                    .fillMaxSize(),
        ) {
            Text("Home Screen")
            AnimateButton()
            SizeAndRotateAnimation()
        }
    }
}

/**
 * Home Screen Composable
 *
 * Here we learn about Transition Animations
 *
 * Using AnimatedVisibility we get to learn more about Transition Animations
 */
@Composable
fun AnimateButton(modifier: Modifier = Modifier) {
    var isVisible by remember { mutableStateOf(false) }
    Button(
        onClick = {
            isVisible = !isVisible
            Log.d("HomeScreenTag", "HomeScreen: isVisible = $isVisible")
        },
    ) {
        Text("Toggle Visibility")
    }
    AnimatedVisibility(visible = isVisible) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.onBackground,
                        RoundedCornerShape(16.dp),
                    ),
        )
    }
}

@Composable
fun SizeAndRotateAnimation(modifier: Modifier = Modifier) {
    var isBig by remember { mutableStateOf(false) }
    val size by animateDpAsState(
        targetValue = if (isBig) 200.dp else 100.dp,
    )
    val animatable =
        remember {
            Animatable(0f)
        }

    val rotation by animateFloatAsState(
        targetValue = if (isBig) 360f else 0f,
    )
//    first appearance
    LaunchedEffect(Unit) {
        animatable.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 2000),
        )
    }

    Box(
        modifier =
            Modifier
                .size(size)
                .graphicsLayer(scaleX = animatable.value, scaleY = animatable.value)
                .rotate(rotation)
                .background(MaterialTheme.colorScheme.primary)
                .clip(RoundedCornerShape(16.dp))
                .clickable { isBig = !isBig },
    )
}
