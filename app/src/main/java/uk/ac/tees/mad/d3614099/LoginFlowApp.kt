package uk.ac.tees.mad.d3614099

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.initialize

class LoginFlowApp: Application() {
    override fun onCreate(){
        super.onCreate()

        Firebase.initialize(this)
    }
}