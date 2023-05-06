package com.example.charitymate

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.charitymate.databinding.HealthAdminItemBinding
import com.example.charitymate.databinding.HungerAdminItemBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class AdminHealthItemsAdapter(private var mList: List<HealthDetails>) :
    RecyclerView.Adapter<AdminHealthItemsAdapter.AdminHealthViewHolder>() {

    inner class AdminHealthViewHolder(var binding: HealthAdminItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminHealthViewHolder {
        val binding = HealthAdminItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdminHealthViewHolder(binding)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: AdminHealthViewHolder, position: Int) {
        with(holder.binding) {
            val item = mList[position]
            itemTitleTextView.text = item.title
            Picasso.get().load(item.pic).into(itemImageView)

            buttonDeleteHungerAdmin.setOnClickListener {
                val db = FirebaseFirestore.getInstance()
                db.collection("HealthDetails").document(item.id)
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