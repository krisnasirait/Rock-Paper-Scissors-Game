package com.krisna.rockpaperscissors

import android.os.Parcel
import android.os.Parcelable

class UserData() : Parcelable {
    var userName : String?=null

    constructor(parcel: Parcel) : this() {
        userName = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserData> {
        override fun createFromParcel(parcel: Parcel): UserData {
            return UserData(parcel)
        }

        override fun newArray(size: Int): Array<UserData?> {
            return arrayOfNulls(size)
        }
    }
}