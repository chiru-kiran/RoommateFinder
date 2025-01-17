package uk.ac.tees.mad.d3614099.presentation.screens.posts

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import uk.ac.tees.mad.d3614099.navigation.Screen
import uk.ac.tees.mad.d3614099.navigation.ScreenRouter
import uk.ac.tees.mad.d3614099.navigation.SystemBackButtonHandler
import uk.ac.tees.mad.d3614099.presentation.common.ButtonComponent2
import uk.ac.tees.mad.d3614099.presentation.common.MyTextFieldComponent2
import uk.ac.tees.mad.d3614099.presentation.common.MyTextFieldComponent3
import uk.ac.tees.mad.d3614099.presentation.common.MyTextFieldComponentNumerical
import uk.ac.tees.mad.d3614099.ui.theme.RoommateFinderTheme

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(
    postViewModel: PostViewModel = viewModel()
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = postViewModel.errorMessage.value) {
        postViewModel.errorMessage.value?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    var imageUris by remember {
        mutableStateOf<List<Uri>>(emptyList())
    }

    val multiplePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris ->
            if (uris.size > 3) {
                Toast.makeText(context, "You can only select 3 images", Toast.LENGTH_SHORT).show()
                return@rememberLauncherForActivityResult
            } else {
                imageUris = uris
                postViewModel.onEvent(PostUiEvents.ImageSelected(uris))
            }
        }
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Room Information")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        ScreenRouter.navigateTo(Screen.ProfileScreen)
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                OutlinedButton(
                    onClick = {
                        multiplePhotoPicker.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Pick Images")
                }

                LazyRow(contentPadding = PaddingValues(6.dp)) {
                    items(imageUris) { uri ->
                        AsyncImage(
                            model = uri,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(150.dp)
                                .clip(RoundedCornerShape(24.dp))
                        )
                    }
                }

                MyTextFieldComponent2(
                    labelValue = "Owner Name",
                    onTextChanged = {
                        postViewModel.onEvent(PostUiEvents.NameChange(it))
                    }
                )

                Spacer(modifier = Modifier.height(6.dp))
                Row(Modifier.fillMaxWidth()) {
                    MyTextFieldComponent2(
                        labelValue = "Gender",
                        onTextChanged = {
                            postViewModel.onEvent(PostUiEvents.GenderChange(it))
                        },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    MyTextFieldComponentNumerical(
                        labelValue = "Owner Phone No",
                        onTextChanged = {
                            postViewModel.onEvent(PostUiEvents.PhoneChange(it))
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                MyTextFieldComponent2(
                    labelValue = "Title",
                    onTextChanged = {
                        postViewModel.onEvent(PostUiEvents.TitleChange(it))
                    }
                )


                Spacer(modifier = Modifier.height(6.dp))

                MyTextFieldComponent3(
                    labelValue = "Room Description",
                    onTextChanged = {
                        postViewModel.onEvent(PostUiEvents.DescriptionChange(it))
                    }
                )

                Spacer(modifier = Modifier.height(6.dp))

                MyTextFieldComponent2(
                    labelValue = "Location",
                    onTextChanged = {
                        postViewModel.onEvent(PostUiEvents.LocationChange(it))
                    })

                Spacer(modifier = Modifier.height(6.dp))
                Row {

                    MyTextFieldComponent2(
                        labelValue = "Duration",
                        onTextChanged = {
                            postViewModel.onEvent(PostUiEvents.DurationChange(it))
                        },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    MyTextFieldComponentNumerical(
                        labelValue = "Price",
                        onTextChanged = {
                            postViewModel.onEvent(PostUiEvents.PriceChange(it))
                        },
                        modifier = Modifier.weight(1f)
                    )
                }



                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Which ones are problem for you?",
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    FlowRow(
                        Modifier
                            .fillMaxWidth(1f)
                            .wrapContentHeight(align = Alignment.Top),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        postViewModel.optionCreativity.fastForEachIndexed { _, element ->
                            val isSelected = postViewModel.selectedOption.contains(element)
                            InputChip(
                                selected = isSelected,
                                modifier = Modifier
                                    .padding(horizontal = 2.dp)
                                    .align(alignment = Alignment.CenterVertically),
                                onClick = {
                                    if (isSelected) {
                                        postViewModel.selectedOption.remove(element)
                                    } else {
                                        postViewModel.selectedOption.add(element)
                                    }
                                },
                                label = { Text(element) }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                ButtonComponent2(
                    value = "Save Info",
                    onButtonClicked = {
                        postViewModel.onEvent(PostUiEvents.PostButtonClicked)
                    },
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .size(300.dp),
            contentAlignment = Alignment.Center,
        ) {
            if (postViewModel.postInProgress.value) {
                CircularProgressIndicator()
            }
        }
    }

    SystemBackButtonHandler {
        ScreenRouter.navigateTo(Screen.ProfileScreen)
    }
}