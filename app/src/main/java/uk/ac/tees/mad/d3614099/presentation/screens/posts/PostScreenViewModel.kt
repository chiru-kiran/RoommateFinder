package uk.ac.tees.mad.d3614099.presentation.screens.posts

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import uk.ac.tees.mad.d3614099.navigation.Screen
import uk.ac.tees.mad.d3614099.navigation.ScreenRouter
import uk.ac.tees.mad.d3614099.presentation.screens.home.RoomData
import java.util.UUID

class PostViewModel : ViewModel() {
    //class RoommateProfileViewModel(private val firebaseRepository: FirebaseRepository) : ViewModel() {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    val TAG = PostViewModel::class.simpleName

    val selectedOption = mutableStateListOf<String>()

    val postUiState = mutableStateOf(PostUiState())

    var postInProgress = mutableStateOf(false)


    var imageUris = mutableStateOf<List<Uri>>(emptyList())


    var errorMessage = mutableStateOf<String?>(null)

    private val database = FirebaseDatabase.getInstance().reference


//    val selectedOptions = remember { mutableStateListOf<String>() }

    fun onEvent(event: PostUiEvents) {
        when (event) {
            is PostUiEvents.NameChange -> {
                postUiState.value = postUiState.value.copy(name = event.name)
            }

            is PostUiEvents.TitleChange -> {
                postUiState.value = postUiState.value.copy(title = event.title)
            }

            is PostUiEvents.DescriptionChange -> {
                postUiState.value = postUiState.value.copy(description = event.description)
            }

            is PostUiEvents.LocationChange -> {
                postUiState.value = postUiState.value.copy(location = event.location)
            }

            is PostUiEvents.PriceChange -> {
                postUiState.value = postUiState.value.copy(price = event.price)
            }

            is PostUiEvents.PhoneChange -> {
                postUiState.value = postUiState.value.copy(phone = event.phone)
            }

            is PostUiEvents.GenderChange -> {
                postUiState.value = postUiState.value.copy(phone = event.gender)
            }

            is PostUiEvents.DurationChange -> {
                postUiState.value = postUiState.value.copy(duration = event.duration)
            }

            is PostUiEvents.ImageSelected -> {
                imageUris.value = event.imageUris
            }

            is PostUiEvents.PostButtonClicked -> {
                submitProfile()
            }
        }
    }

    val optionCreativity = listOf(
        "Drinker", "Smoker", "Pets", "Loud Music",
        "Messy", "Night Owl", "Non-Vegetarian", "Opposite Gender"
    )


    private fun submitProfile() {
        viewModelScope.launch {
            postInProgress.value = true

            val name = postUiState.value.name
            val title = postUiState.value.title
            val description = postUiState.value.description
            val location = postUiState.value.location
            val duration = postUiState.value.duration
            val price = postUiState.value.price
            val phone = postUiState.value.phone
            val selectedOption = selectedOption


            // Generate unique key
            val key = database.push().key

            // Store image URLs
            val imageUrls = mutableListOf<String>()

            // Create a reference to Firebase Storage
            val storage = Firebase.storage

            // Upload each image to Firebase Storage
            for (uri in imageUris.value) {
                val imageRef = storage.reference.child("images/${UUID.randomUUID()}")
                //This line starts the upload process.
                // It creates an UploadTask that uploads the local
                // file represented by uri to the file in Firebase
                // Storage represented by imageRef.
                val uploadTask = imageRef.putFile(uri)

                // Get the download URL and add it to the imageUrls list
                try {
                    val downloadUrl = uploadTask.continueWithTask { task ->
                        if (!task.isSuccessful) {
                            task.exception?.let {
                                throw it
                            }
                        }
                        imageRef.downloadUrl
                    }.await()

                    imageUrls.add(downloadUrl.toString())
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to upload image: ${e.localizedMessage}")
                }
            }

            // Create a map to store user data
            val postData = hashMapOf(
                "name" to name,
                "title" to title,
                "description" to description,
                "location" to location,
                "duration" to duration,
                "price" to price,
                "phone" to phone,
                "preferences" to selectedOption,
                "images" to imageUrls
            )

            // Save data under the unique key in the database
            try {
                if (key != null) {
                    withContext(Dispatchers.IO) {
                        database.child(key).setValue(postData).await()
                    }
                    postInProgress.value = false
                    Log.d(TAG, "Data saved successfully")
                    //navigate to home screen
                    ScreenRouter.navigateTo(Screen.HomeScreen)
                }
            } catch (e: Exception) {
                Log.d(TAG, "Data failed")
                errorMessage.value = "Failed to Save: ${e.localizedMessage}"
                postInProgress.value = false
            }


//            if (key != null) {
//                database.child(key).setValue(postData)
//                    .addOnSuccessListener {
//                        // Upload successful
//                        postInProgress.value = false
//                        Log.d(TAG, "Inside_login_success")
//
//                        ScreenRouter.navigateTo(Screen.HomeScreen)
//                    }
//                    .addOnFailureListener {
//                        // Upload failed
//                        postInProgress.value = false
//                        Log.d(TAG, "Inside_login_failure")
//                    }
//            } else {
//                // Handle key generation error
//            }
        }
    }

    val posts = mutableStateOf<List<RoomData>>(emptyList())

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                //Get reference to Firebase Database
                val dbRef = FirebaseDatabase.getInstance().getReference()

                //Fetch data from Firebase
                val dataSnapshot = dbRef.get().await()

                //Convert the data to a list of RoomData objects
                val posts = dataSnapshot.children.mapNotNull { it.getValue(RoomData::class.java) }

                // Log the fetched data
                posts.forEach { post ->
                    Log.d(TAG, "Fetched post: $post")
                }

                //update saate var
                this@PostViewModel.posts.value = posts
            } catch (e: Exception) {
                Log.e(TAG, "Failed to fetch posts: ${e.localizedMessage}")
            }
        }
    }
}

