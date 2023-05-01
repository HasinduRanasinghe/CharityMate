package com.example.charitymate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_to_charity_mate)
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