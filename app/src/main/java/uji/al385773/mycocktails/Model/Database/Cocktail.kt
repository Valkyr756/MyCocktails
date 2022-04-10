package uji.al385773.mycocktails.Model.Database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
@Entity(foreignKeys = [ForeignKey(
        entity = Category::class,
        parentColumns = ["name"],
        childColumns = ["category"]
    )],
    indices = [Index(value = ["name"], unique = true)],
)
data class Cocktail(
    val name: String,
    val isAlcoholic: String,
    val glass: String,
    val instructions: String,
    val category: String,
    val imageUrl: String,
    var score: Int,

    @PrimaryKey
    val id: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(isAlcoholic)
        parcel.writeString(glass)
        parcel.writeString(instructions)
        parcel.writeString(category)
        parcel.writeString(imageUrl)
        parcel.writeInt(score)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cocktail> {
        override fun createFromParcel(parcel: Parcel): Cocktail {
            return Cocktail(parcel)
        }

        override fun newArray(size: Int): Array<Cocktail?> {
            return arrayOfNulls(size)
        }
    }
}
