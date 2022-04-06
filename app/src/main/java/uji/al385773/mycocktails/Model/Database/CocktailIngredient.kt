package uji.al385773.mycocktails.Model.Database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    primaryKeys = ["cocktailID", "ingredientName"],
    foreignKeys = [ForeignKey(
        entity = Cocktail::class,
        parentColumns = ["id"],
        childColumns = ["cocktailID"]
    ),
    ForeignKey(
        entity = Ingredient::class,
        parentColumns = ["name"],
        childColumns = ["ingredientName"]
    )],
    indices = [Index(value = ["id"], unique = true), Index(value = ["name"], unique = true)],
)

data class CocktailIngredient(
    val cocktailID: Int,
    val measures: String,
    val ingredientName: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(cocktailID)
        parcel.writeString(measures)
        parcel.writeString(ingredientName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CocktailIngredient> {
        override fun createFromParcel(parcel: Parcel): CocktailIngredient {
            return CocktailIngredient(parcel)
        }

        override fun newArray(size: Int): Array<CocktailIngredient?> {
            return arrayOfNulls(size)
        }
    }
}
