package com.example.charitymate

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.charitymate.databinding.ActivityAdminMicrofinanceBinding
import com.google.firebase.firestore.FirebaseFirestore

class AdminMf : AppCompatActivity() {

    private lateinit var binding: ActivityAdminMicrofinanceBinding
    private lateinit var firebaseFirestore: FirebaseFirestore
    private var mList = mutableListOf<MfDetails>()
    private lateinit var adapter: AdminMfItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminMicrofinanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val addDonationButton = findViewById<Button>(R.id.addDonation)
        addDonationButton.setOnClickListener {
            val intent = Intent(this, AddMfDonations::class.java)
            startActivity(intent)
        }

        initVars()
        getData()

    }

    private fun initVars() {
        firebaseFirestore = FirebaseFirestore.getInstance()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AdminMfItemsAdapter(mList)
        binding.recyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")

    private fun getData() {
        firebaseFirestore.collection("MicrofinanceDetails")
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
                    val mfDetails = MfDetails(id, title, description, location, amountNeeded, contact, startDate, endDate, imageUrl)
                    mList.add(mfDetails)
                }
                adapter.notifyDataSetChanged()
            }
    }
}