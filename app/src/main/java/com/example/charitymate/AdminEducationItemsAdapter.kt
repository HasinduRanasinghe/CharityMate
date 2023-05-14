package com.example.charitymate

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.charitymate.databinding.EducationAdminItemBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class AdminEducationItemsAdapter(private var mList: List<EducationDetails>) :
    RecyclerView.Adapter<AdminEducationItemsAdapter.AdminEducationViewHolder>() {

    inner class AdminEducationViewHolder(var binding: EducationAdminItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminEducationViewHolder {
        val binding = EducationAdminItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdminEducationViewHolder(binding)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: AdminEducationViewHolder, position: Int) {
        with(holder.binding) {
            val item = mList[position]
            itemTitleTextView.text = item.title
            Picasso.get().load(item.pic).into(itemImageView)

            buttonDeleteEducationAdmin.setOnClickListener {
                val db = FirebaseFirestore.getInstance()
                db.collection("EducationDetails").document(item.id)
                    .delete()
                    .addOnSuccessListener {
                        mList = mList.filter { it.id != item.id }
                        notifyDataSetChanged()
                    }
            }

            buttonEditEducationAdmin.setOnClickListener {
                showUpdateForm(holder, item)
            }

        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    private fun showUpdateForm(holder: AdminEducationViewHolder, item: EducationDetails) {
        val updateFormView = LayoutInflater.from(holder.itemView.context)
            .inflate(R.layout.update_form, null)

        val titleEditText = updateFormView.findViewById<EditText>(R.id.updateTitle)
        val descriptionEditText = updateFormView.findViewById<EditText>(R.id.updateDescription)
        val contactEditText = updateFormView.findViewById<EditText>(R.id.updateContact)
        val startDateEditText = updateFormView.findViewById<EditText>(R.id.updateStartDate)
        val endDateEditText = updateFormView.findViewById<EditText>(R.id.updateEndDate)

        titleEditText.setText(item.title)
        descriptionEditText.setText(item.description)
        contactEditText.setText(item.contact)
        startDateEditText.setText(item.startDate)
        endDateEditText.setText(item.endDate)

        AlertDialog.Builder(holder.itemView.context)
            .setTitle("Update Item")
            .setView(updateFormView)
            .setPositiveButton("Update") { dialog, _ ->
                val updatedTitle = titleEditText.text.toString()
                val updatedDescription = descriptionEditText.text.toString()
                val updatedContact = contactEditText.text.toString()
                val updatedStartDate = startDateEditText.text.toString()
                val updatedEndDate = endDateEditText.text.toString()
                val updatedPic = item.pic

                updateItem(item, updatedTitle, updatedDescription, updatedContact, updatedStartDate, updatedEndDate, updatedPic)

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun updateItem(
        item: EducationDetails,
        updatedTitle: String,
        updatedDescription: String,
        updatedContact: String,
        updatedStartDate: String,
        updatedEndDate: String,
        updatedPic: String,
    ) {
        val db = FirebaseFirestore.getInstance()

        val updatedData = hashMapOf(
            "title" to updatedTitle,
            "description" to updatedDescription,
            "contact" to updatedContact,
            "startDate" to updatedStartDate,
            "endDate" to updatedEndDate,
            "pic" to updatedPic,
            "amountNeeded" to item.amountNeeded,
            "location" to item.location
        )

        db.collection("EducationDetails").document(item.id)
            .set(updatedData)
            .addOnSuccessListener {
            }
    }
}