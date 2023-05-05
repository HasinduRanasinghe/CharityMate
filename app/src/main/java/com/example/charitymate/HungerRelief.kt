package com.example.charitymate

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.charitymate.databinding.ActivityAddHungerDonationsBinding
import com.example.charitymate.databinding.HungerReliefBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class HungerRelief : AppCompatActivity() {

    private lateinit var binding: HungerReliefBinding
    private lateinit var firebaseFirestore: FirebaseFirestore
    private var mList = mutableListOf<HungerDetails>()
    private lateinit var adapter: HungerItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HungerReliefBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVars()
        getImages()

    }

    private fun initVars() {
        firebaseFirestore = FirebaseFirestore.getInstance()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = HungerItemsAdapter(mList)
        binding.recyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
//    private fun getImages() {
//
//        firebaseFirestore.collection("HungerDetails")
//            .get().addOnSuccessListener {
//                for(i in it) {
//                    mList.add(i.data["pic"].toString())
//                }
//                adapter.notifyDataSetChanged()
//            }
//    }
    private fun getImages() {
        firebaseFirestore.collection("HungerDetails")
            .get()
            .addOnSuccessListener { documents ->
                mList.clear()
                for (document in documents) {
                    val data = document.data
                    val title = data["title"].toString()
                    val description = data["description"].toString()
                    val amountNeeded = data["amountNeeded"].toString()
                    val contact = data["contact"].toString()
                    val startDate = data["startDate"].toString()
                    val endDate = data["endDate"].toString()
                    val imageUrl = data["pic"].toString()
                    //mList.add("$title\n$description\nAmount Needed: $amountNeeded\nContact: $contact\nStart Date: $startDate\nEnd Date: $endDate\n$imageUrl")
                    val hungerDetails = HungerDetails(title, description, amountNeeded, contact, startDate, endDate, imageUrl)
                    mList.add(hungerDetails)
                }
                adapter.notifyDataSetChanged()
            }
    }
}