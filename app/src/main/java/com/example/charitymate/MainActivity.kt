package com.example.charitymate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.welcome_to_charity_mate)
//        setContentView(R.layout.activity_add_hunger_donations)
        //setContentView(R.layout.education_donation)

        //setContentView(R.layout.hunger_relief)
        //setContentView(R.layout.activity_admin_hunger)
//        setContentView(R.layout.activity_add_hunger_donations)
//        setContentView(R.layout.activity_edit_hunger_details)

         //Navigate to UserWelcomeActivity when the "Get Started" button is clicked
//        val getStartedButton = findViewById<ImageButton>(R.id.imageButton)
//        getStartedButton.setOnClickListener {
//            val intent = Intent(this, ::class.java)
//            startActivity(intent)
//        }
//        val getStartedButton = findViewById<Button>(R.id.button3)
//        getStartedButton.setOnClickListener {
//            val intent = Intent(this, AddHungerDonations::class.java)
//            startActivity(intent)
//        }
//        val getStartedButton = findViewById<Button>(R.id.btnAll)
//        getStartedButton.setOnClickListener {
//            val intent = Intent(this, HungerRelief::class.java)
//            startActivity(intent)
//        }
//        val getStartedButton = findViewById<Button>(R.id.buttonSave)
//        getStartedButton.setOnClickListener {
//            val intent = Intent(this, EditHungerDetailsActivity::class.java)
//            startActivity(intent)
//        }
//        val getStartedButton = findViewById<Button>(R.id.addDonation)
//        getStartedButton.setOnClickListener {
//            val intent = Intent(this, AdminHunger::class.java)
//            startActivity(intent)
//        }
//        val getStartedButton = findViewById<Button>(R.id.button3)
//        getStartedButton.setOnClickListener {
//            val intent = Intent(this, AddHungerDonations::class.java)
//            startActivity(intent)
//        }
//        val getStartedButton = findViewById<Button>(R.id.button3)
//        getStartedButton.setOnClickListener {
//            val intent = Intent(this, AddEducationDonations::class.java)
//            startActivity(intent)
//        }
//        setContentView(R.layout.activity_add_health_donations)
//        val getStartedButton = findViewById<Button>(R.id.button3)
//        getStartedButton.setOnClickListener {
//            val intent = Intent(this, AddHealthDonations::class.java)
//            startActivity(intent)
//        }

//        setContentView(R.layout.health)
//        val getStartedButton = findViewById<Button>(R.id.btnAll)
//        getStartedButton.setOnClickListener {
//            val intent = Intent(this, Health::class.java)
//            startActivity(intent)
//        }

        setContentView(R.layout.activity_admin_health)
        val getStartedButton = findViewById<Button>(R.id.addDonation)
        getStartedButton.setOnClickListener {
            val intent = Intent(this, AdminHealth::class.java)
            startActivity(intent)
        }

    }

    fun displayUserWelcome(view: View){
        setContentView(R.layout.user_welcome)
    }

    fun displayWelcomePage(view: View){
        setContentView(R.layout.welcome_to_charity_mate)
    }

    fun displayRegisterPage(view: View){
        setContentView(R.layout.register_page)
    }


}

