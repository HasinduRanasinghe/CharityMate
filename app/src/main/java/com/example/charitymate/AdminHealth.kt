package com.example.charitymate

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.charitymate.databinding.ActivityAdminHealthBinding
import com.google.firebase.firestore.FirebaseFirestore

class AdminHealth : AppCompatActivity() {
    private lateinit var binding: ActivityAdminHealthBinding
    private lateinit var firebaseFirestore: FirebaseFirestore
    private var mList = mutableListOf<HealthDetails>()
    private lateinit var adapter: AdminHealthItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminHealthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val addDonationButton = findViewById<Button>(R.id.addDonation)
        addDonationButton.setOnClickListener {
            val intent = Intent(this, AddHealthDonations::class.java)
            startActivity(intent)
        }

        binding.adminProfIcon.setOnClickListener {
            val intent = Intent(this, AdminProfile::class.java)
            startActivity(intent)
        }

        initVars()
        getData()

    }

    private fun initVars() {
        firebaseFirestore = FirebaseFirestore.getInstance()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AdminHealthItemsAdapter(mList)
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