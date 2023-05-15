package com.example.charitymate

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MakeAHealthDonation : AppCompatActivity(){
    private lateinit var firestore: FirebaseFirestore
    private lateinit var id: String
    private var amountNeeded: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.make_donation)

        firestore = FirebaseFirestore.getInstance()

        id = intent.getStringExtra("documentId") ?: ""
        amountNeeded = intent.getDoubleExtra("amountNeeded", 0.0)

        val btnConfirm = findViewById<Button>(R.id.btnConfirmed)
        btnConfirm.setOnClickListener {
            onConfirmClicked()
        }
    }

    private fun onConfirmClicked() {
        val editAmount = findViewById<EditText>(R.id.editAmount)
        val spnPayMethod = findViewById<Spinner>(R.id.spnPayMethod)
        val editCardNumber = findViewById<EditText>(R.id.editCardNumber)
        val editDate = findViewById<EditText>(R.id.editDate)
        val editCVV = findViewById<EditText>(R.id.editCVV)

        val amount = editAmount.text.toString()
        val paymentMethod = spnPayMethod.selectedItem.toString()
        val cardNumber = editCardNumber.text.toString()
        val expirationDate = editDate.text.toString()
        val cvv = editCVV.text.toString()

        if (amount.isEmpty()) {
            editAmount.error = "Amount is required"
            return
        }

        if (cardNumber.isEmpty()) {
            editCardNumber.error = "Card number is required"
            return
        }

        if (expirationDate.isEmpty()) {
            editDate.error = "Expiration date is required"
            return
        }

        if (cvv.isEmpty()) {
            editCVV.error = "CVV is required"
            return
        }

        if (cvv.length != 3) {
            editCVV.error = "CVV should have 3 digits"
            return
        }
        savePayment(amount, paymentMethod, cardNumber, expirationDate, cvv)
    }

    private fun savePayment(
        amount: String,
        paymentMethod: String,
        cardNumber: String,
        expirationDate: String,
        cvv: String
    ) {
        val amountValue = amount.toDoubleOrNull() ?: 0.0

        val payment = hashMapOf(
            "amount" to amountValue,
            "paymentMethod" to paymentMethod,
            "cardNumber" to cardNumber,
            "expirationDate" to expirationDate,
            "cvv" to cvv
        )

        firestore.collection("Payments")
            .add(payment)
            .addOnSuccessListener { documentReference ->
                // Payment saved successfully
                Toast.makeText(
                    this@MakeAHealthDonation,
                    "Payment Successfull",
                    Toast.LENGTH_SHORT
                ).show()

                firestore.collection("HealthDetails").document(id)
                    .get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            val currentAmountNeeded = document.getDouble("amountNeeded") ?: 0.0
                            val donatedAmount = amountValue
                            val newAmountNeeded = if (currentAmountNeeded >= donatedAmount) currentAmountNeeded - donatedAmount else 0.0

                            val updatedData = hashMapOf(
                                "amountNeeded" to newAmountNeeded
                            )
                            firestore.collection("HealthDetails").document(id)
                                .update(updatedData as Map<String, Any>)
                                .addOnSuccessListener {
                                    finish()
                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(
                                        this@MakeAHealthDonation,
                                        "Error updating amount needed: ${e.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        }
                    }
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    this@MakeAHealthDonation,
                    "Payment error: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}