package ir.laitec.model

import android.os.Parcel
import android.os.Parcelable

data class userModel(var username : String, var password : String, var type : Int) : Parcelable {

    var address : String
        get() = this.toString()
        set(value) {
            address = value
        }

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()
    )

    override fun toString(): String {
        super.toString()



        return "username : " + username + "password : " + password + "type : " + type
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeInt(type)
    }




    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<userModel> {
        override fun createFromParcel(parcel: Parcel): userModel {
            return userModel(parcel)
        }

        override fun newArray(size: Int): Array<userModel?> {
            return arrayOfNulls(size)
        }
    }
}





