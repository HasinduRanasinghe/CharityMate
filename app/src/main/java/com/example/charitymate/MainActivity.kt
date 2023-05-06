package com.example.charitymate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
//        setContentView(R.layout.activity_add_microfinance_donation)
//
//        val getStartedButton = findViewById<Button>(R.id.button3)
//        getStartedButton.setOnClickListener {
//            val intent = Intent(this, AddMfDonations::class.java)
//            startActivity(intent)
//            }

//        setContentView(R.layout.microfinance)
//
//        val getStartedButton = findViewById<Button>(R.id.btnAll)
//        getStartedButton.setOnClickListener {
//            val intent = Intent(this, Microfinance::class.java)
//            startActivity(intent)
//        }

        setContentView(R.layout.activity_admin_microfinance)

        val getStartedButton = findViewById<Button>(R.id.addDonation)
        getStartedButton.setOnClickListener {
            val intent = Intent(this, AdminMf::class.java)
            startActivity(intent)
        }
    }
}