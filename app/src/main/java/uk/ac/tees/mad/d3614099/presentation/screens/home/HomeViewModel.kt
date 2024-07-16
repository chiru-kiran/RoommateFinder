package uk.ac.tees.mad.d3614099.presentation.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeViewModel : ViewModel() {
    private val _rooms = MutableLiveData<List<RoomData>>()
    val rooms: LiveData<List<RoomData>> = _rooms

    private val databaseReference = FirebaseDatabase.getInstance().getReference("rooms")

    init {
        fetchData()
    }

    private fun fetchData() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<RoomData>()
                snapshot.children.forEach { it ->
                    val room = it.getValue(RoomData::class.java)
                    room?.let { list.add(it) }
                }
                _rooms.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error here
            }
        })
    }
}