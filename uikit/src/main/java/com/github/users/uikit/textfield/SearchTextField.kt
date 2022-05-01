package com.github.users.uikit.textfield

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.github.users.uikit.utils.emptyField
import com.github.users.uikit.R


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextField(
    @StringRes placeholder: Int,
    onValueChange: ((String) -> Unit)? = null,
    onSearchClicked: ((String) -> Unit)? = null
) {
    var message by remember {
        mutableStateOf(emptyField)
    }

    var showTrailingIcons by remember {
        mutableStateOf(false)
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Row(modifier = Modifier.fillMaxWidth()) {
        val focusRequester = remember { FocusRequester() }
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }

        TextField(
            value = message,
            colors = TextFieldDefaults.textFieldColors(
                textColor = colorResource(R.color.black),
                cursorColor = colorResource(R.color.black),
                backgroundColor = colorResource(id = R.color.white),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            placeholder = {
                Text(
                    text = stringResource(id = placeholder),
                    color = colorResource(R.color.black),
                )
            },
            trailingIcon = {
                if (showTrailingIcons) {
                    Row(
                        modifier = Modifier.padding(8.dp, 0.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_clear_white),
                            tint = colorResource(
                                id = R.color.black,
                            ),
                            contentDescription = "clear text",
                            modifier = Modifier
                                .padding(8.dp, 0.dp)
                                .clickable {
                                    message = emptyField
                                }
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search_small_white),
                            tint = colorResource(
                                id = R.color.black,
                            ),
                            contentDescription = "start search",
                            modifier = Modifier
                                .padding(8.dp, 0.dp)
                                .clickable {
                                    keyboardController?.hide()
                                    onSearchClicked?.let { it(message.text) }
                                }
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                    onSearchClicked?.let { it(message.text) }
                }
            ),
            onValueChange = { textFieldValue ->
                if (textFieldValue.text != message.text) {
                    message = textFieldValue
                    onValueChange?.let { onValueChange -> onValueChange(message.text) }
                }
                showTrailingIcons = textFieldValue.text.isNotEmpty()
            },
            singleLine = true
        )
    }
}