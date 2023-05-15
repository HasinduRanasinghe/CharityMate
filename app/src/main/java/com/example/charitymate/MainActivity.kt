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
        setContentView(R.layout.welcome_to_charity_mate)

        val startButton = findViewById<ImageButton>(R.id.imageButton)
        startButton.setOnClickListener {
            val intent = Intent(this, UserWelcomeActivity::class.java)
            startActivity(intent)
        }

//        setContentView(R.layout.activity_admin_hunger)
//        val getStartedButton = findViewById<Button>(R.id.addDonation)
//        getStartedButton.setOnClickListener {
//            val intent = Intent(this, AdminHunger::class.java)
//            startActivity(intent)
//        }

//        setContentView(R.layout.hunger_relief)
//        val getStartedButton = findViewById<Button>(R.id.btnAll)
//        getStartedButton.setOnClickListener {
//            val intent = Intent(this, HungerRelief::class.java)
//            startActivity(intent)
//        }

//        setContentView(R.layout.activity_admin_health)
//        val getStartedButton = findViewById<Button>(R.id.addDonation)
//        getStartedButton.setOnClickListener {
//            val intent = Intent(this, AdminHealth::class.java)
//            startActivity(intent)
//        }

//        setContentView(R.layout.health)
//        val getStartedButton = findViewById<Button>(R.id.btnAll)
//        getStartedButton.setOnClickListener {
//            val intent = Intent(this, Health::class.java)
//            startActivity(intent)
//        }


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

