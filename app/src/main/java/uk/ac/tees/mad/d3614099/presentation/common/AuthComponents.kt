package uk.ac.tees.mad.d3614099.presentation.common

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
//import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
//import androidx.compose.material.icons.filled.Visibility
//import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.d3614099.R
import uk.ac.tees.mad.d3614099.ui.theme.componentShape

@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ), color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ), color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldComponent(
    labelValue: String,
    painterResource: Painter,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false
) {

    val textValue = remember {
        mutableStateOf("")
    }
    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShape.small),
        label = { Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = colorResource(id = R.color.colorPrimary),
            unfocusedTextColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.colorPrimary),
            focusedBorderColor = colorResource(id = R.color.colorPrimary),
            unfocusedBorderColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.colorPrimary),
            unfocusedLabelColor = colorResource(id = R.color.black),
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextChanged(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !errorStatus
    )
}

@Composable
fun MyTextFieldComponent2(
    labelValue: String,
//    painterResource: Painter,
    onTextChanged: (String) -> Unit
) {

    val textValue = remember {
        mutableStateOf("")
    }
    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShape.small),
        label = { Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = colorResource(id = R.color.colorPrimary),
            unfocusedTextColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.colorPrimary),
            focusedBorderColor = colorResource(id = R.color.colorPrimary),
            unfocusedBorderColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.colorPrimary),
            unfocusedLabelColor = colorResource(id = R.color.black),
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextChanged(it)
        }
//        leadingIcon = {
//            Icon(painter = painterResource, contentDescription = "")
//        }
    )
}

@Composable
fun MyTextFieldComponentNumerical(
    labelValue: String,
//    painterResource: Painter,
    onTextChanged: (String) -> Unit
) {

    val textValue = remember {
        mutableStateOf("")
    }
    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShape.small),
        label = { Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = colorResource(id = R.color.colorPrimary),
            unfocusedTextColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.colorPrimary),
            focusedBorderColor = colorResource(id = R.color.colorPrimary),
            unfocusedBorderColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.colorPrimary),
            unfocusedLabelColor = colorResource(id = R.color.black),
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextChanged(it)
        }
//        leadingIcon = {
//            Icon(painter = painterResource, contentDescription = "")
//        }
    )
}

@Composable
fun MyTextFieldComponent3(
    labelValue: String,
//    painterResource: Painter,
    onTextChanged: (String) -> Unit
) {

    val textValue = remember {
        mutableStateOf("")
    }
    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShape.small),
        label = { Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = colorResource(id = R.color.colorPrimary),
            unfocusedTextColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.colorPrimary),
            focusedBorderColor = colorResource(id = R.color.colorPrimary),
            unfocusedBorderColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.colorPrimary),
            unfocusedLabelColor = colorResource(id = R.color.black),
        ),
//        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Unspecified),
        maxLines = 2,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextChanged(it)
        }
//        leadingIcon = {
//            Icon(painter = painterResource, contentDescription = "")
//        }
    )
}

@Composable
fun PasswordTextFieldComponent(
    labelValue: String, painterResource: Painter,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false
) {

    val localFocusManager = LocalFocusManager.current
    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShape.medium),
        label = { Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(id = R.color.colorPrimary),
            focusedLabelColor = colorResource(id = R.color.colorPrimary),
            cursorColor = colorResource(id = R.color.colorPrimary),
//            backgroundColor = colorResource(id = R.color.colorSecondary)
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        maxLines = 1,
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        trailingIcon = {

            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            val description = if (passwordVisible.value) {
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }

        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus
    )
}

@Composable
fun CheckboxComponent(
    value: String,
    onTextSelected: (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        val checkedState = remember {
            mutableStateOf(false)
        }

        Checkbox(checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = !checkedState.value
                onCheckedChange.invoke(it)
            })

        ClickableTextComponent(value = value, onTextSelected)
    }
}

@Composable
fun ClickableTextComponent(value: String, onTextSelected: (String) -> Unit) {
    val initialText = "By continuing you accept our "
    val privacyPolicyText = "Privacy Policy"
    val andText = " and "
    val termsAndConditionsText = "Term of Use"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.colorPrimary))) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.colorPrimary))) {
            pushStringAnnotation(tag = termsAndConditionsText, annotation = termsAndConditionsText)
            append(termsAndConditionsText)
        }
    }

    ClickableText(text = annotatedString, onClick = { offset ->

        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent", "{${span.item}}")

                if ((span.item == termsAndConditionsText) || (span.item == privacyPolicyText)) {
                    onTextSelected(span.item)
                }
            }

    })
}

@Composable
fun ButtonComponent(value: String, onButtonClicked: () -> Unit, isEnabled: Boolean = false) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        onClick = {
            onButtonClicked.invoke()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp),
        enabled = isEnabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            colorResource(id = R.color.colorPrimary),
                            colorResource(id = R.color.colorSecondary)
                        )
                    ),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

        }

    }
}

@Composable
fun ButtonComponent2(value: String, onButtonClicked: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        onClick = {
            onButtonClicked.invoke()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            colorResource(id = R.color.colorPrimary),
                            colorResource(id = R.color.colorSecondary)
                        )
                    ),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ClickableLoginTextComponent(tryingToLogin: Boolean = true, onTextSelected: (String) -> Unit) {
    val initialText =
        if (tryingToLogin) "Already have an account? " else "Don’t have an account yet? "
    val loginText = if (tryingToLogin) "Login" else "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.colorPrimary))) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString,
        onClick = { offset ->

            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")

                    if (span.item == loginText) {
                        onTextSelected(span.item)
                    }
                }

        },
    )
}

@Composable
fun UnderLinedTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ), color = colorResource(id = R.color.colorGrey),
        textAlign = TextAlign.End,
        textDecoration = TextDecoration.Underline
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    toolbarTitle: String, logoutButtonClicked: () -> Unit,
    navigationIconClicked: () -> Unit
) {

    TopAppBar(
//        colors = TopAppBarDefaults.topAppBarColors(
//            backgroundColor = colorResource(id = R.color.colorPrimary),
//            contentColor = colorResource(id = R.color.colorText
//            )
//        ),
        title = {
            Text(
                text = toolbarTitle, color = colorResource(id = R.color.colorText)
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navigationIconClicked.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = colorResource(id = R.color.colorText)
                )
            }

        },
        actions = {
            IconButton(onClick = {
                logoutButtonClicked.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.Logout,
                    contentDescription = "Logout",
                )
            }
        }
    )
}

@Composable
fun NavigationDrawerHeader(value: String?) {
    Box(
        modifier = Modifier
//            .background(
//                Brush.horizontalGradient(
//                    listOf(Primary, Secondary)
//                )
//            )
            .fillMaxWidth()
            .height(180.dp)
            .padding(32.dp)
    ) {

        NavigationDrawerText(
            title = value ?: "Navigation Header", 28.sp, colorResource(id = R.color.colorAccent)
        )

    }
}

//@Composable
//fun NavigationDrawerBody(
//    navigationDrawerItems: List<NavigationItem>,
//    onNavigationItemClicked: (NavigationItem) -> Unit
//) {
//    LazyColumn(modifier = Modifier.fillMaxWidth()) {
//
//        items(navigationDrawerItems) {
//            NavigationItemRow(item = it, onNavigationItemClicked)
//        }
//
//    }
//}

//@Composable
//fun NavigationItemRow(
//    item: NavigationItem,
//    onNavigationItemClicked: (NavigationItem) -> Unit
//) {
//
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable {
//                onNavigationItemClicked.invoke(item)
//            }
//            .padding(all = 16.dp)
//    ) {
//
//        Icon(
//            imageVector = item.icon,
//            contentDescription = item.description,
//        )
//
//        Spacer(modifier = Modifier.width(18.dp))
//
//        NavigationDrawerText(title = item.title, 18.sp, Primary)
//
//
//    }
//}

@Composable
fun NavigationDrawerText(title: String, textUnit: TextUnit, color: Color) {

    val shadowOffset = Offset(4f, 6f)

    Text(
        text = title, style = TextStyle(
            color = Color.Black,
            fontSize = textUnit,
            fontStyle = FontStyle.Normal,
            shadow = Shadow(
                color = colorResource(id = R.color.colorPrimary),
                offset = shadowOffset, 2f
            )
        )
    )
}