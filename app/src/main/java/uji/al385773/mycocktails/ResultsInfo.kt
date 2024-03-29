package uji.al385773.mycocktails

import android.os.Parcel
import android.os.Parcelable

data class ResultsInfo(val category: String, val ingredient: String, val isCategory: Boolean, val isInetSearch: Boolean) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(category)
        parcel.writeString(ingredient)
        parcel.writeByte(if (isCategory) 1 else 0)
        parcel.writeByte(if (isInetSearch) 1 else 0)
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
