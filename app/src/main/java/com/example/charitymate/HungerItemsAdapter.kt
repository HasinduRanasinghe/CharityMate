//package com.example.charitymate
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.charitymate.databinding.HungerItemBinding
//import com.squareup.picasso.Picasso
//
//class HungerItemsAdapter (private var mList:List<String>) : RecyclerView.Adapter<HungerItemsAdapter.ImagesViewHolder> (){
//
//    inner class ImagesViewHolder(var binding : HungerItemBinding) : RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
//        val binding = HungerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ImagesViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
//        with(holder.binding){
//           // with(mList[position]){
//                //Picasso.get().load(this).into(itemImageView)
//            Picasso.get().load(mList[position]).into(itemImageView)
//           // }
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return mList.size
//    }
//}
package com.example.charitymate

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.charitymate.databinding.HungerItemBinding
import com.squareup.picasso.Picasso

class HungerItemsAdapter(private var mList: List<HungerDetails>) :
    RecyclerView.Adapter<HungerItemsAdapter.HungerViewHolder>() {

    inner class HungerViewHolder(var binding: HungerItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HungerViewHolder {
        val binding = HungerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HungerViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HungerViewHolder, position: Int) {
        with(holder.binding) {
            val item = mList[position]
            itemTitleTextView.text = item.title
            itemDescriptionTextView.text = item.description
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