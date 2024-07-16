package uk.ac.tees.mad.d3614099.presentation.screens.posts

import android.net.Uri

sealed class PostUiEvents {
    data class NameChange(val name: String) : PostUiEvents()
    data class TitleChange(val title: String) : PostUiEvents()
    data class DescriptionChange(val description: String) : PostUiEvents()
    data class LocationChange(val location: String) : PostUiEvents()
    data class DurationChange(val duration: String) : PostUiEvents()
    data class PriceChange(val price: String) : PostUiEvents()
    data class PhoneChange(val phone: String) : PostUiEvents()

    data class GenderChange(val gender: String) : PostUiEvents()
    data class ImageSelected(val imageUris: List<Uri>) : PostUiEvents()
    data object PostButtonClicked : PostUiEvents()
}