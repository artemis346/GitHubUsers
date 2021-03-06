package com.github.users.userdetails

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.users.uikit.error.ErrorView
import com.github.users.uikit.shimmer.DetailsShimmer
import com.github.users.userdetails.dto.UserDetailItem
import com.github.users.userdetails.viewmodel.UserDetailsUiState
import com.github.users.userdetails.viewmodel.UserDetailsViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
internal fun UserDetailScreen(navController: NavHostController, userId: String?) {
    val vm: UserDetailsViewModel = hiltViewModel()
    Scaffold(
        backgroundColor = colorResource(id = R.color.white),
        topBar = { ScreenHeader(navController) },
        content = { ScreenContent(vm = vm, userId) }
    )
}

@Composable
internal fun ScreenHeader(navController: NavHostController) {
    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back button")
            }
        },
        backgroundColor = colorResource(id = R.color.white),
        contentColor = colorResource(id = R.color.black),
        elevation = 0.dp
    )
}

@Composable
internal fun ScreenContent(vm: UserDetailsViewModel, userId: String?) {
    LaunchedEffect(Unit) {
        vm.fetchUserDetails(userId)
    }
    when (val state = vm.uiState.collectAsState().value) {
        is UserDetailsUiState.Success -> {
            UserCard(state.item, vm)
        }
        is UserDetailsUiState.Loading -> {
            DetailsShimmer()
        }
        is UserDetailsUiState.Error -> {
            ErrorView(stringResource(state.error.message)) {
                vm.fetchUserDetails(userId)
            }
        }
        else -> {
            Log.v("state", "Initial state")
        }
    }
}

@Composable
internal fun UserCard(user: UserDetailItem, vm: UserDetailsViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        HeaderRow(
            login = user.login,
            name = user.name,
            image = user.avatarUrl,
            user.isInFavorite,
            vm
        )
        user.location?.let {
            LocationRow(location = it)
        }
        user.companyName?.let {
            CompanyRow(company = it)
        }
        user.email?.let {
            EmailRow(email = it)
        }
        FollowersRow(followers = user.followers, following = user.following)
        RepositoryRow(user.publicRepos)
    }
}

@Composable
internal fun HeaderRow(
    login: String,
    name: String?,
    image: String?,
    isInFavorite: Boolean,
    vm: UserDetailsViewModel
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        constraintSet = decoupledConstraints()
    ) {
        GlideImage(
            modifier = Modifier
                .layoutId(LayoutIds.imageAvatar)
                .clip(CircleShape)
                .height(80.dp)
                .width(80.dp),
            imageModel = image,
            contentScale = ContentScale.FillBounds,
        )
        Text(
            text = login,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.layoutId(LayoutIds.textUserLogin),
            color = colorResource(com.github.users.uikit.R.color.black),
        )
        name?.let {
            Text(
                text = name,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.layoutId(LayoutIds.textUserName),
                color = colorResource(com.github.users.uikit.R.color.black),
            )
        }

        val checked = remember { mutableStateOf(isInFavorite) }
        FavoriteButton(checked) {
            vm.setUserInFavorite(it)
            checked.value = it
        }
    }
}

@Composable
internal fun FavoriteButton(
    currentChecked: MutableState<Boolean>,
    onCheckable: (checked: Boolean) -> Unit
) {
    OutlinedButton(
        onClick = {
            onCheckable(!currentChecked.value)
        },
        modifier = Modifier
            .layoutId(LayoutIds.buttonIsInFavorite)
            .wrapContentWidth()
            .wrapContentHeight(),
        border = BorderStroke(1.dp, colorResource(id = com.github.users.uikit.R.color.purple_500))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                imageVector = if (currentChecked.value) {
                    Icons.Filled.Star
                } else {
                    Icons.Filled.Add
                },
                contentDescription = "Favorite filter",
                tint = colorResource(com.github.users.uikit.R.color.purple_500)
            )
            Text(
                modifier = Modifier
                    .layoutId("buttonContent")
                    .padding(horizontal = 16.dp, vertical = 0.dp),
                color = colorResource(com.github.users.uikit.R.color.purple_500),
                text = if (currentChecked.value) {
                    stringResource(id = R.string.remove_from_favorite)
                } else {
                    stringResource(id = R.string.add_to_favorite)
                }
            )
        }
    }
}

@Composable
internal fun LocationRow(location: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 6.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp)
                .align(CenterVertically),
            imageVector = Icons.Outlined.Place,
            contentDescription = "user location"
        )
        Text(
            modifier = Modifier
                .align(CenterVertically)
                .padding(10.dp, 0.dp),
            text = location,
            fontSize = 16.sp,
            color = colorResource(com.github.users.uikit.R.color.black)
        )
    }
}

@Composable
internal fun CompanyRow(company: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 6.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp)
                .align(CenterVertically),
            imageVector = Icons.Outlined.Info,
            contentDescription = "company"
        )
        Text(
            modifier = Modifier
                .align(CenterVertically)
                .padding(10.dp, 0.dp),
            text = company,
            fontSize = 16.sp,
            color = colorResource(com.github.users.uikit.R.color.black)
        )
    }
}

@Composable
internal fun EmailRow(email: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 6.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp)
                .align(CenterVertically),
            imageVector = Icons.Outlined.Email,
            contentDescription = "Back button"
        )
        Text(
            modifier = Modifier
                .align(CenterVertically)
                .padding(10.dp, 0.dp),
            text = email,
            fontSize = 16.sp,
            color = colorResource(com.github.users.uikit.R.color.black)
        )
    }
}

@Composable
internal fun FollowersRow(followers: Int, following: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 6.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp)
                .align(CenterVertically),
            imageVector = Icons.Outlined.Person,
            contentDescription = "followers"
        )
        Text(
            modifier = Modifier
                .align(CenterVertically)
                .padding(10.dp, 0.dp),
            text = stringResource(id = R.string.followers, followers.toString()),
            fontSize = 16.sp,
            color = colorResource(com.github.users.uikit.R.color.black)
        )
        Box(
            modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .align(CenterVertically)
                .background(colorResource(id = R.color.black))
        )
        Text(
            modifier = Modifier
                .align(CenterVertically)
                .padding(10.dp, 0.dp),
            text = stringResource(id = R.string.following, following.toString()),
            fontSize = 16.sp,
            color = colorResource(com.github.users.uikit.R.color.black)
        )
    }
}

@Composable
internal fun RepositoryRow(publicRepos: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(21.dp)
                .align(CenterVertically),
            imageVector = Icons.Outlined.List,
            contentDescription = "repsIcon"
        )
        Text(
            modifier = Modifier
                .padding(10.dp, 0.dp)
                .weight(1.0F, true)
                .alignByBaseline(),
            text = stringResource(id = R.string.repositories),
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            color = colorResource(com.github.users.uikit.R.color.black)
        )
        Text(
            modifier = Modifier
                .padding(5.dp, 0.dp)
                .alignByBaseline(),
            text = publicRepos.toString(),
            fontSize = 16.sp,
            color = colorResource(com.github.users.uikit.R.color.black)
        )
    }
}

internal fun decoupledConstraints(): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor(LayoutIds.imageAvatar)
        val title = createRefFor(LayoutIds.textUserLogin)
        val name = createRefFor(LayoutIds.textUserName)
        val isInFavorite = createRefFor(LayoutIds.buttonIsInFavorite)

        constrain(image) {
            visibility
            linkTo(
                parent.top,
                parent.bottom,
                topMargin = 16.dp,
                bottomMargin = 16.dp,
                bias = 0f
            )
            start.linkTo(parent.start, 16.dp)
        }
        constrain(title) {
            top.linkTo(parent.top, 16.dp)
            linkTo(image.end, parent.end, startMargin = 16.dp, endMargin = 8.dp, bias = 0f)
        }
        constrain(name) {
            top.linkTo(title.bottom, 8.dp)
            linkTo(image.end, parent.end, startMargin = 16.dp, endMargin = 8.dp, bias = 0f)
        }
        constrain(isInFavorite) {
            linkTo(name.bottom, parent.bottom, topMargin = 16.dp, bottomMargin = 16.dp)
            linkTo(image.end, parent.end, startMargin = 16.dp, endMargin = 8.dp, bias = 0f)
        }
    }
}

internal object LayoutIds {
    const val imageAvatar: String = "avatarImage"
    const val textUserLogin: String = "textUserLogin"
    const val textUserName: String = "textUserName"
    const val buttonIsInFavorite: String = "buttonIsInFavorite"
}

