package com.example.charitymate

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.charitymate.databinding.MfItemBinding
import com.squareup.picasso.Picasso

class MfItemsAdapter(private var mList: List<MfDetails>) :
    RecyclerView.Adapter<MfItemsAdapter.MfViewHolder>() {

    inner class MfViewHolder(var binding: MfItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MfViewHolder {
        val binding = MfItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MfViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MfViewHolder, position: Int) {
        with(holder.binding) {
            val item = mList[position]
            itemTitleTextView.text = item.title
            itemDescriptionTextView.text = item.description
            itemLocationTextView.text = item.location
            itemAmountTextView.text = "Amount Needed: ${item.amountNeeded}"
            itemContactTextView.text = "Contact: ${item.contact}"
            itemStartDateTextView.text = "Start Date: ${item.startDate}"
            itemEndDateTextView.text = "End Date: ${item.endDate}"
            Picasso.get().load(item.pic).into(itemImageView)

        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}