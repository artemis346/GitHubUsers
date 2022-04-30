package com.github.users.uikit.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.github.users.uikit.R
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ItemList(
    title: String,
    image: String,
    subTitle: String? = null,
    onClick: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        constraintSet = decoupledConstraints()
    ) {
        GlideImage(
            modifier = Modifier
                .layoutId("image")
                .clip(CircleShape)
                .height(80.dp)
                .width(80.dp),
            imageModel = image,
            contentScale = ContentScale.FillBounds,
        )
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.layoutId("title"),
            color = colorResource(R.color.black),
        )
        subTitle?.let {
            Text(
                text = subTitle,
                fontSize = 14.sp,
                modifier = Modifier.layoutId("subtitle"),
                color = colorResource(R.color.black),
            )
        }
    }
}

private fun decoupledConstraints(): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val title = createRefFor("title")
        val subtitle = createRefFor("subtitle")

        constrain(image) {
            linkTo(parent.top, parent.bottom, topMargin = 16.dp, bottomMargin = 16.dp, bias = 0f)
            start.linkTo(parent.start, 16.dp)
        }
        constrain(title) {
            top.linkTo(parent.top, 16.dp)
            linkTo(image.end, parent.end, startMargin = 16.dp, endMargin = 8.dp, bias = 0f)
        }

        constrain(subtitle) {
            linkTo(image.end, parent.end, 16.dp, 16.dp, bias = 0f)
            linkTo(title.bottom, parent.bottom, 8.dp, 16.dp, bias = 0f)
        }
    }
}