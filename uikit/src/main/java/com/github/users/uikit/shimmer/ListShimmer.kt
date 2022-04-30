package com.github.users.uikit.shimmer

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

const val DEFAULT_SHIMMER_SIZE = 5

@Composable
fun ListShimmer(shimmerSize: Int = DEFAULT_SHIMMER_SIZE) {
    Column {
        repeat(shimmerSize) {
            AnimatedShimmer()
        }
    }
}