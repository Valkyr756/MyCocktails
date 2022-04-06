package uji.al385773.mycocktails.Model.Database

import android.os.Parcel
import android.os.Parcelable

class CocktailBundle(val cocktail: Cocktail, val cocktailIngredients: List<CocktailIngredient>): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Cocktail::class.java.classLoader)!!,
        parcel.createTypedArrayList(CocktailIngredient)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(cocktail, flags)
        parcel.writeTypedList(cocktailIngredients)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CocktailBundle> {
        override fun createFromParcel(parcel: Parcel): CocktailBundle {
            return CocktailBundle(parcel)
        }

        override fun newArray(size: Int): Array<CocktailBundle?> {
            return arrayOfNulls(size)
        }
    }
}