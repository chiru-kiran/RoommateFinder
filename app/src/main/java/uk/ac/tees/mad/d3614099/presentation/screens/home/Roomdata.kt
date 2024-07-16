package uk.ac.tees.mad.d3614099.presentation.screens.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RoomData @JvmOverloads constructor(
    val id: Int = 0,
    val images: List<String> = emptyList(),
    val name: String = "",
    val gender: String = "",
    val phone: String = "",
    val title: String = "",
    val description: String = "",
    val location: String = "",
    val duration: String = "",
    val currentLocation: String = "",
    val price: String= "",
    val ownerImage: Int = 0,
    val preferences: List<String>  = listOf()
): Parcelable
//val roomDataList = listOf(
//    RoomData(
//        images = listOf(
//            R.drawable.home_image1,
//            R.drawable.home_image2,
//            R.drawable.home_image3
//        ),
//        city = "London",
//        rentalDuration = "6 months",
//        price = "£2000",
//        ownerName = "John Doe",
//        ownerImage = R.drawable.profile_pic,
//        ownerContact = "1234567890"
//
//    ),
//    RoomData(
//        images = listOf(
//            R.drawable.home_image3,
//            R.drawable.home_image2,
//            R.drawable.home_image1
//        ),
//        city = "Ealing",
//        rentalDuration = "5 months",
//        price = "£5000",
//        ownerName = "Zesica",
//        ownerImage = R.drawable.profile_pic_2,
//        ownerContact = "1234567890"
//    ),
//    RoomData(
//        images = listOf(
//            R.drawable.home_image2,
//            R.drawable.home_image3,
//            R.drawable.home_image1
//        ),
//        city = "Greenwich",
//        rentalDuration = "16 months",
//        price = "£19000",
//        ownerName = "Christ",
//        ownerImage = R.drawable.profile_pic,
//        ownerContact = "1234567890"
//    ),
//    RoomData(
//        images = listOf(
//            R.drawable.home_image3,
//            R.drawable.home_image1,
//            R.drawable.home_image2
//        ),
//        city = "Hillingdon",
//        rentalDuration = "4 months",
//        price = "£1000",
//        ownerName = "John Doe",
//        ownerImage = R.drawable.profile_pic_2,
//        ownerContact = "1234567890"
//    )
//)
