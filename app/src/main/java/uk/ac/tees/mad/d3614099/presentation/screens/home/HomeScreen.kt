package uk.ac.tees.mad.d3614099.presentation.screens.home

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardDoubleArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.google.gson.Gson
import uk.ac.tees.mad.d3614099.R
import uk.ac.tees.mad.d3614099.location.LocationUtils
import uk.ac.tees.mad.d3614099.navigation.Screen
import uk.ac.tees.mad.d3614099.navigation.ScreenRouter
import uk.ac.tees.mad.d3614099.presentation.screens.posts.PostScreen
import uk.ac.tees.mad.d3614099.presentation.screens.posts.PostViewModel
import uk.ac.tees.mad.d3614099.presentation.screens.profile.ProfileData

@Composable
fun HomeScreen(
    postViewModel: PostViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel(),
    userProfile: ProfileData = ProfileData(
        name = "John Doe",
        profileImage = 0
    )
) {

    val context = LocalContext.current

    var location by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(key1 = Unit) {
        location = LocationUtils().getCurrentLocation(context as Activity)
    }


    val rooms by homeViewModel.rooms.observeAsState(emptyList())

    Surface(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Welcome ${userProfile.name}",
                        style = MaterialTheme.typography.headlineMedium,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = null
                        )
                        Text(
//                            text = userProfile.location,
                            text = location ?: "London, UK",
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(id = R.drawable.profile_pic),
                    contentDescription = "Image",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onSurface,
                            shape = CircleShape
                        )
                        .clickable {
                            ScreenRouter.navigateTo(Screen.ProfileScreen)
                        },
                    contentScale = ContentScale.Crop
                )
            }
            HorizontalDivider()
            LazyColumn(
                modifier = Modifier.weight(1.0f),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                item {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

                        Text(
                            text = "Find the best rooms here",
                            style = MaterialTheme.typography.labelSmall
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.Default.KeyboardDoubleArrowDown,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                items(postViewModel.posts.value) { room ->
                    RoomCard(room = room) { selectedRoom ->
                        // Navigate to Detail Screen on click
                        ScreenRouter.navigateToDetailScreen(selectedRoom)
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            FloatingActionButton(
                onClick = { ScreenRouter.navigateTo(Screen.PostScreen) },
                elevation = FloatingActionButtonDefaults.elevation(8.dp),
                contentColor = colorResource(id = R.color.fab),
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Post",
                    tint = Color.Black
                )
            }
        }
    }
}

@Composable
fun RoomCard(room: RoomData, onClick: (RoomData) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = {
                onClick(room)
                ScreenRouter.navigateToDetailScreen(room)
            }),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Column {
            Box(modifier = Modifier.height(200.dp)) {
                ImagePager(images = room.images)
            }
            HorizontalDivider(color = Color.Black)
            Column(Modifier.padding(12.dp)) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = room.location,
                        style = MaterialTheme.typography.labelLarge,
                        fontSize = 19.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Timer,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "${room.duration} months",
                        style = MaterialTheme.typography.labelMedium,
                        fontSize = 15.sp,
                        fontWeight = FontWeight(500),
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
//                Image(
//                    painter = rememberAsyncImagePainter(model = room.ownerImage),
//                    contentDescription = "Owner Image",
//                    modifier = Modifier
//                        .size(50.dp)
//                        .clip(CircleShape)
//                )
//
//                Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Owner: ${room.name}",
                        style = MaterialTheme.typography.labelMedium,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(700),
                    )

                    Spacer(modifier = Modifier.weight(1.0f))

                    Text(
                        text = "Rent: € ${room.price} /Mon",
                        style = MaterialTheme.typography.labelMedium,
                        fontSize = 19.sp,
                        fontWeight = FontWeight(700)
                    )

                }
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagePager(images: List<String>) {

    val pagerState = rememberPagerState(pageCount = { images.size })

    Box(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(state = pagerState) { page ->
            Image(
                painter = rememberAsyncImagePainter(model = images[page]),
                contentDescription = "Room Image $page",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        val indicatorDots = Modifier.align(Alignment.BottomCenter)

        Row(
            modifier =
            indicatorDots
                .padding(bottom = 8.dp)
        ) {
            for (i in images.indices) {
                val isSelected = pagerState.currentPage == i
                val color =
                    if (isSelected) MaterialTheme.colorScheme.inverseSurface else Color.LightGray
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(color, shape = CircleShape)
                )
            }
        }
    }
}


//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen(
//        SignupViewModel(),
//        userProfile = ProfileData(
//            name = "John Doe",
//            profileImage = 0
//        )
//    )
//}