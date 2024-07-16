package uk.ac.tees.mad.d3614099.preferences

import android.content.Context
import android.content.SharedPreferences
import uk.ac.tees.mad.d3614099.R

class UserPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

    fun saveUserName(name: String) {
        sharedPreferences.edit().putString("user_name", name).apply()
    }

    fun getUserName(): String {
        return sharedPreferences.getString("user_name", "John Doe") ?: "John Doe"
    }

    fun saveUserGender(gender: String) {
        sharedPreferences.edit().putString("user_gender", gender).apply()
    }

    fun getUserGender(): String {
        return sharedPreferences.getString("user_gender", "M") ?: "M"
    }

    fun saveUserImageUri(imageUri: String) {
        sharedPreferences.edit().putString("user_image_uri", imageUri).apply()
    }

    fun getUserImageUri(): Any {
        val uri = sharedPreferences.getString("user_image_uri", null)
        return uri ?: R.drawable.profile_pic
    }
}