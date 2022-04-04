package uji.al385773.mycocktails

import android.os.Parcel
import android.os.Parcelable

data class DetailsInfo(val name: String, val alcoholic: String, val glass: String, val category: String, val instructions: String, val ingredients: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(alcoholic)
        parcel.writeString(glass)
        parcel.writeString(category)
        parcel.writeString(instructions)
        parcel.writeString(ingredients)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DetailsInfo> {
        override fun createFromParcel(parcel: Parcel): DetailsInfo {
            return DetailsInfo(parcel)
        }

        override fun newArray(size: Int): Array<DetailsInfo?> {
            return arrayOfNulls(size)
        }
    }
}
