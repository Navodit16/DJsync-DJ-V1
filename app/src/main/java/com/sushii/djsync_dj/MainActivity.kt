package com.sushii.djsync_dj

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var firestore: FirebaseFirestore
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        firestore = FirebaseFirestore.getInstance()
        adapter = UserAdapter()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        fetchData()

    }
    private fun fetchData() {
        firestore.collection("Users")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show()
                    return@addSnapshotListener
                }

                if (snapshot != null && !snapshot.isEmpty) {
                    val usersList = mutableListOf<User>()
                    for (document in snapshot.documents) {
                        val name = document.getString("name") ?: ""
                        val selectedSong0 = document.getString("selectedSong0") ?: ""
                        val selectedSong1 = document.getString("selectedSong1") ?: ""
                        val selectedSong2 = document.getString("selectedSong2") ?: ""
                        val selectedSong3 = document.getString("selectedSong3") ?: ""
                        val selectedSong4 = document.getString("selectedSong4") ?: ""

                        val user = User(name, selectedSong0, selectedSong1, selectedSong2, selectedSong3, selectedSong4)
                        usersList.add(user)
                    }

                    // Update RecyclerView on the main thread
                    runOnUiThread {
                        adapter.setUserList(usersList)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
    }


}