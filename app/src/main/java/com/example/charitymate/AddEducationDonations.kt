package com.example.charitymate

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.charitymate.databinding.EducationDonationBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class AddEducationDonations : AppCompatActivity() {
    private lateinit var binding: EducationDonationBinding
    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var storageRef: StorageReference
    private var imageURI: Uri?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EducationDonationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVars()
        registerClickEvents()
    }

    private fun registerClickEvents() {
        binding.button3.setOnClickListener {

            uploadImage()
        }

        binding.addImage.setOnClickListener {

            resultLauncher.launch("image/*")
        }

    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()){

        imageURI = it
        binding.addImage.setImageURI(it)
    }
    private fun initVars() {
        storageRef = FirebaseStorage.getInstance().reference.child("EducationDetails")
        firebaseFirestore = FirebaseFirestore.getInstance()
    }

    private fun uploadImage() {

        storageRef = storageRef.child(System.currentTimeMillis().toString())
        imageURI?.let {
            storageRef.putFile(it).addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    storageRef.downloadUrl.addOnSuccessListener { uri ->
                        val title = binding.EditTextTitle.text.toString().trim()
                        val description = binding.EditTextDescription.text.toString().trim()
                        val location = binding.editEducationName.text.toString().trim()
                        val contact = binding.editContact.text.toString().trim()
                        val amountNeeded = binding.editTextEnterAmount.text.toString().toDouble()
                        val startDate = binding.editStartDate.text.toString().trim()
                        val endDate = binding.editEndDate.text.toString().trim()

                        // Check if any of the required fields are empty
                        if (title.isEmpty() || description.isEmpty() || location.isEmpty() || contact.isEmpty() || amountNeeded == 0.0 || startDate.isEmpty() || endDate.isEmpty()) {
                            Toast.makeText(this, "Please fill in all the required fields", Toast.LENGTH_SHORT).show()
                        }

                        val map = HashMap<String, Any>()
                        map["pic"] = uri.toString()
                        map["title"] = title
                        map["description"] = description
                        map["location"] = location
                        map["contact"] = contact
                        map["amountNeeded"] = amountNeeded
                        map["startDate"] = startDate
                        map["endDate"] = endDate

                        firebaseFirestore.collection("EducationDetails").add(map)
                            .addOnCompleteListener { firestoreTask ->
                                if (firestoreTask.isSuccessful) {
                                    Toast.makeText(
                                        this,
                                        "Uploaded Successfully",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this,
                                        firestoreTask.exception?.message,
                                        Toast.LENGTH_LONG
                                    ).show()
                                }

                                binding.addImage.setImageResource(R.drawable.baseline_image_24)
                            }
                    }
                } else {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                    binding.addImage.setImageResource(R.drawable.baseline_image_24)
                }
            }
        }
    }


}
