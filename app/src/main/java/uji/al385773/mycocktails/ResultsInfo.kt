package uji.al385773.mycocktails

import android.os.Parcel
import android.os.Parcelable
import uji.al385773.mycocktails.Model.Database.Category
import uji.al385773.mycocktails.Model.Database.Ingredient

data class ResultsInfo(val category: String, val ingredient: String, val searchChoice: Boolean) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(category)
        parcel.writeString(ingredient)
        parcel.writeByte(if (searchChoice) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResultsInfo> {
        override fun createFromParcel(parcel: Parcel): ResultsInfo {
            return ResultsInfo(parcel)
        }

        override fun newArray(size: Int): Array<ResultsInfo?> {
            return arrayOfNulls(size)
        }
    }

}
