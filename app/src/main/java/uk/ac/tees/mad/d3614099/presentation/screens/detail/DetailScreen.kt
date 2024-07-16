package uk.ac.tees.mad.d3614099.presentation.screens.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import uk.ac.tees.mad.d3614099.R
import uk.ac.tees.mad.d3614099.navigation.Screen
import uk.ac.tees.mad.d3614099.navigation.ScreenRouter
import uk.ac.tees.mad.d3614099.navigation.SystemBackButtonHandler
import uk.ac.tees.mad.d3614099.presentation.screens.home.ImagePager
import uk.ac.tees.mad.d3614099.presentation.screens.home.RoomData

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(room:RoomData) {

//    val navArgs = rememberNavController().currentBackStackEntry?.arguments
//    val room = navArgs?.getParcelable<RoomData>("room")
//    if (room!=null){
//        DetailScreen(room = room)
//    }
    val showContactDetails = remember { mutableStateOf(false) }

    val isClicked = remember {
        mutableStateOf(false)
    }

    Scaffold(
        bottomBar = {
            if (!showContactDetails.value) {
                BottomAppBar(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(24.dp))
                ) {
                    Text(
                        text = room.price,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(16.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = { showContactDetails.value = true },
                        modifier = Modifier
                            .padding(16.dp)
                            .size(150.dp)
                    ) {
                        Text(
                            text = "Contact",
                        )
                    }
                }
            } else
                BottomAppBar(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(24.dp))
                ) {

                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null
                    )
                    Text(
                        text = room.phone,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(16.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = { showContactDetails.value = false },
                        modifier = Modifier
                            .padding(16.dp)
                            .size(150.dp)
                    ) {
                        Text(text = "Close")
                    }
                }
        }
    ) {
        Surface(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Box(
                    modifier = Modifier
                        .height(400.dp)
                        .clip(RoundedCornerShape(24.dp))
                ) {
                    ImagePager(images = room.images)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = null
                    )
                    Text(
                        text = room.location,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = room.duration,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = "Description",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(
                            text = room.description,
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Owner",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(start = 8.dp,top = 8.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        val imageId = if (room.ownerImage != 0) room.ownerImage else R.drawable.profile_pic
                        Image(
                            painter = painterResource(id = imageId),
                            contentDescription = "Owner Image",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = room.name,
                                style = MaterialTheme.typography.headlineMedium
                            )
                        }
                    }
                }
            }
        }
        SystemBackButtonHandler {
            ScreenRouter.navigateTo(Screen.HomeScreen)
        }

    }
}


//@Preview
//@Composable
//fun PreviewDetailScreen() {
//    DetailScreen(
//        room = RoomData(
//            images = listOf(
//                R.drawable.home_image1,
//                R.drawable.home_image2
//            ),
//            ownerImage = R.drawable.profile_pic,
//            city = "London",
//            rentalDuration = "1 year",
//            price = "£1000",
//            ownerName = "John Doe",
//            ownerContact = "+63 453456365"
//        )
//    )
//}

