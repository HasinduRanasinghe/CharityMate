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

        // Navigate to UserWelcomeActivity when the "Get Started" button is clicked
        val getStartedButton = findViewById<ImageButton>(R.id.imageButton)
        getStartedButton.setOnClickListener {
            val intent = Intent(this, UserWelcomeActivity::class.java)
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

