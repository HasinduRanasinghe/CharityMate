package com.example.charitymate

import android.location.Location
import android.os.Parcel
import android.os.Parcelable

class HungerDetails(
    var id: String,
    var title: String,
    var description: String,
    var location: String,
    var amountNeeded: String,
    var contact: String,
    var startDate: String,
    var endDate: String,
    var pic: String
)
//): Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readString()!!,
//        parcel.readString()!!,
//        parcel.readString()!!,
//        parcel.readString()!!,
//        parcel.readString()!!,
//        parcel.readString()!!,
//        parcel.readString()!!,
//        parcel.readString()!!,
//        parcel.readString()!!
//    )
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(title)
//        parcel.writeString(description)
//        parcel.writeString(location)
//        parcel.writeString(amountNeeded)
//        parcel.writeString(contact)
//        parcel.writeString(startDate)
//        parcel.writeString(endDate)
//        parcel.writeString(pic)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<HungerDetails> {
//        override fun createFromParcel(parcel: Parcel): HungerDetails {
//            return HungerDetails(parcel)
//        }
//
//        override fun newArray(size: Int): Array<HungerDetails?> {
//            return arrayOfNulls(size)
//        }
//    }
//}
