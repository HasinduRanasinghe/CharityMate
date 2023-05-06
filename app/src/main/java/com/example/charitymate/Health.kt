package com.example.charitymate

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.charitymate.databinding.HealthBinding
import com.google.firebase.firestore.FirebaseFirestore

class Health : AppCompatActivity(){
    private lateinit var binding: HealthBinding
    private lateinit var firebaseFirestore: FirebaseFirestore
    private var mList = mutableListOf<HealthDetails>()
    private lateinit var adapter: HealthItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HealthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVars()
        getData()

    }

    private fun initVars() {
        firebaseFirestore = FirebaseFirestore.getInstance()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = HealthItemsAdapter(mList)
        binding.recyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")

    private fun getData() {
        firebaseFirestore.collection("HealthDetails")
            .get()
            .addOnSuccessListener { documents ->
                mList.clear()
                for (document in documents) {
                    val data = document.data
                    val id = document.id
                    val title = data["title"].toString()
                    val description = data["description"].toString()
                    val location = data["location"].toString()
                    val amountNeeded = data["amountNeeded"].toString()
                    val contact = data["contact"].toString()
                    val startDate = data["startDate"].toString()
                    val endDate = data["endDate"].toString()
                    val imageUrl = data["pic"].toString()
                    val healthDetails = HealthDetails(id, title, description, location, amountNeeded, contact, startDate, endDate, imageUrl)
                    mList.add(healthDetails)
                }
                adapter.notifyDataSetChanged()
            }
    }
}