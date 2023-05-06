package com.example.charitymate

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
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

        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}