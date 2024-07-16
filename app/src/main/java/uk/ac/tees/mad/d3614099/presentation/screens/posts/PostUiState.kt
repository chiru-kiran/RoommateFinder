package uk.ac.tees.mad.d3614099.presentation.screens.posts

data class PostUiState(
    val images: List<String> = listOf(),
    val name: String = "",
    val title: String = "",
    val description: String = "",
    val location: String = "",
    val duration: String = "",
    val price: String = "",
    val phone: String = "",
    val gender: String = "",
    val preferences: List<String>  = listOf()
)
