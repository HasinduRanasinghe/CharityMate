package com.example.charitymate

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.charitymate.databinding.HungerAdminItemBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class AdminHungerItemsAdapter(private var mList: List<HungerDetails>) :
    RecyclerView.Adapter<AdminHungerItemsAdapter.AdminHungerViewHolder>() {

    inner class AdminHungerViewHolder(var binding: HungerAdminItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminHungerViewHolder {
        val binding = HungerAdminItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdminHungerViewHolder(binding)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: AdminHungerViewHolder, position: Int) {
        with(holder.binding) {
            val item = mList[position]
            itemTitleTextView.text = item.title
            Picasso.get().load(item.pic).into(itemImageView)

            buttonDeleteHungerAdmin.setOnClickListener {
                val db = FirebaseFirestore.getInstance()
                db.collection("HungerDetails").document(item.id)
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