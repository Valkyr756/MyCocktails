package uji.al385773.mycocktails.Model.Database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
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
    val ingredientName: String
)
