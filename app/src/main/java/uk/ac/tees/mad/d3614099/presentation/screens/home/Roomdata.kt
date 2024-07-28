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
