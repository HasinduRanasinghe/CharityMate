package com.example.charitymate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.charitymate.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.Signup.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

//        binding.LoginButton.setOnClickListener {
//            val email = binding.EmailText.text.toString()
//            val password = binding.PasswordText.text.toString()
//
//            if (email.isNotEmpty() && password.isNotEmpty()) {
//                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        val intent = Intent(this, UserProfile::class.java)
//                        startActivity(intent)
//                    } else {
//                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
//                    }
//                }
//            } else {
//                Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_LONG).show()
//            }
//        }
        binding.LoginButton.setOnClickListener {
            val email = binding.EmailText.text.toString()
            val password = binding.PasswordText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { loginTask ->
                    if (loginTask.isSuccessful) {
                        val userId = firebaseAuth.currentUser?.uid
                        val userRef = FirebaseDatabase.getInstance().reference.child("Users").child(userId!!)
                        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {

                                val name = dataSnapshot.child("name").value as? String
                                val email = dataSnapshot.child("email").value as? String
                                val username = dataSnapshot.child("username").value as? String
                                val contact = dataSnapshot.child("contact").value as? String

                                if (name != null && email != null && username != null && contact != null) {
                                    sessionManager.setLoggedIn(true)
                                    sessionManager.setProfileDetails(name, email, username, contact)

                                    val intent = Intent(this@LoginActivity, UserProfile::class.java)
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(this@LoginActivity, "Failed to retrieve user details", Toast.LENGTH_LONG).show()
                                }
                            }

                            override fun onCancelled(databaseError: DatabaseError) {
                                Toast.makeText(this@LoginActivity, databaseError.message, Toast.LENGTH_LONG).show()
                            }
                        })
                    } else {
                        Toast.makeText(this@LoginActivity, loginTask.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this@LoginActivity, "Empty fields are not allowed", Toast.LENGTH_LONG).show()
            }
        }
    }
}