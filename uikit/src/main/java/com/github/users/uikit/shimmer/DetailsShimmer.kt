package com.github.users.uikit.shimmer

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DetailsShimmer() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.7f),
        Color.LightGray.copy(alpha = 0.3f),
        Color.LightGray.copy(alpha = 0.7f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    ShimmerDetailGridItem(brush = brush)
}

@Composable
fun ShimmerDetailGridItem(brush: Brush) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(brush)
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Column(verticalArrangement = Arrangement.Top) {
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .width(200.dp)
                        .background(brush)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Spacer(
                    modifier = Modifier
                        .height(15.dp)
                        .width(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(brush)
                )
            }
        }
        for (i in 1..3) {
            Spacer(modifier = Modifier.padding(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(
                    modifier = Modifier
                        .size(18.dp)
                        .clip(CircleShape)
                        .background(brush)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Spacer(
                    modifier = Modifier
                        .height(15.dp)
                        .width(300.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(brush)
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Spacer(
            modifier = Modifier
                .height(30.dp)
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(brush)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ShimmerDetailGridItemPreview() {
    ShimmerDetailGridItem(
        brush = Brush.linearGradient(
            listOf(
                Color.LightGray.copy(alpha = 0.7f),
                Color.LightGray.copy(alpha = 0.3f),
                Color.LightGray.copy(alpha = 0.7f),
            )
        )
    )
}