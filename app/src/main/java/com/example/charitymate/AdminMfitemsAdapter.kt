package com.example.charitymate

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.charitymate.databinding.MfAdminItemBinding

import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class AdminMfItemsAdapter(private var mList: List<MfDetails>) :
    RecyclerView.Adapter<AdminMfItemsAdapter.AdminMfViewHolder>() {

    inner class AdminMfViewHolder(var binding: MfAdminItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminMfViewHolder {
        val binding = MfAdminItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdminMfViewHolder(binding)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: AdminMfViewHolder, position: Int) {
        with(holder.binding) {
            val item = mList[position]
            itemTitleTextView.text = item.title
            Picasso.get().load(item.pic).into(itemImageView)

            buttonDeleteMfAdmin.setOnClickListener {
                val db = FirebaseFirestore.getInstance()
                db.collection("MicrofinanceDetails").document(item.id)
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