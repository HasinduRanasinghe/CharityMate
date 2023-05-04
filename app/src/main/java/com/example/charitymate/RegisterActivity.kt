package com.example.charitymate

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue

class RegisterActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var numberEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var reenterPasswordEditText: EditText
    private lateinit var registerButton: Button

    //Creating a database reference
    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_page)

        nameEditText = findViewById(R.id.regAsAGiverName)
        emailEditText = findViewById(R.id.regAsAGiverEmail)
        usernameEditText = findViewById(R.id.regAsAGiverUname)
        numberEditText = findViewById(R.id.regAsAGiverTel)
        passwordEditText = findViewById(R.id.regAsAGiverPassword)
        reenterPasswordEditText = findViewById(R.id.regAsAGiverRePassword)
        registerButton = findViewById(R.id.button_RegGiverSbmt)

        db = FirebaseDatabase.getInstance().getReference("Users")

        registerButton.setOnClickListener{
            registerUser()
        }

    }

    private fun registerUser() {

        //Getting values
        val name = nameEditText.text.toString()
        val email = emailEditText.text.toString()
        val username = usernameEditText.text.toString()
        val number = numberEditText.text.toString()
        val password = passwordEditText.text.toString()
        val reenterPassword = reenterPasswordEditText.text.toString()

        //Validations
        if(name.isEmpty()){
            nameEditText.error = "Please enter name"
        }
        if(email.isEmpty()){
            emailEditText.error = "Please enter email"
        }
        if(username.isEmpty()){
            usernameEditText.error = "Please enter username"
        }
        if(number.isEmpty()){
            numberEditText.error = "Please enter number"
        }
        if(password.isEmpty()){
            passwordEditText.error = "Please enter password"
        }
        if(reenterPassword.isEmpty()){
            reenterPasswordEditText.error = "Please re-enter password"
        }

        if (password != reenterPassword) {
            val snackbar = Snackbar.make(registerButton, "Password do not match", Snackbar.LENGTH_LONG)
            snackbar.setAction("OK") {
                snackbar.dismiss()
            }
            snackbar.setActionTextColor(resources.getColor(R.color.white))
            snackbar.setTextColor(resources.getColor(android.R.color.white))
            snackbar.setBackgroundTint(resources.getColor(R.color.black))
            snackbar.show()
            //Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show()
            return
        }

        if(name.isEmpty() || email.isEmpty() || username.isEmpty() || number.isEmpty() || password.isEmpty() || reenterPassword.isEmpty()){
            val snackbar = Snackbar.make(registerButton, "Please fill in all required fields", Snackbar.LENGTH_LONG)
            snackbar.setAction("OK") {
                snackbar.dismiss()
            }
            snackbar.setActionTextColor(resources.getColor(R.color.white))
            snackbar.setTextColor(resources.getColor(android.R.color.white))
            snackbar.setBackgroundTint(resources.getColor(R.color.black))
            snackbar.show()
            //Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_LONG).show()
            return
        }

        //Auto generating an userID
        val userID = db.push().key!!

        //Creating a user object
        val user = User(userID, name, email, username, number, password)

        val timestamp = ServerValue.TIMESTAMP

        //Database related implementations
        db.child(userID).setValue(user)
            .addOnCompleteListener{
                val snackbar = Snackbar.make(registerButton, "Data inserted successfully", Snackbar.LENGTH_LONG)
                snackbar.setAction("OK") {
                    snackbar.dismiss()
                }
                snackbar.setActionTextColor(resources.getColor(R.color.white))
                snackbar.setTextColor(resources.getColor(android.R.color.white))
                snackbar.setBackgroundTint(resources.getColor(R.color.black))
                snackbar.show()
                //Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener{ err ->
                val snackbar = Snackbar.make(registerButton, "Error ${err.message}", Snackbar.LENGTH_LONG)
                snackbar.setAction("OK") {
                    snackbar.dismiss()
                }
                snackbar.setActionTextColor(resources.getColor(R.color.white))
                snackbar.setTextColor(resources.getColor(android.R.color.white))
                snackbar.setBackgroundTint(resources.getColor(R.color.black))
                snackbar.show()
                //Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}