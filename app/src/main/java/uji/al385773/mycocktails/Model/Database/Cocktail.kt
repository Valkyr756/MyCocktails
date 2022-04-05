package uji.al385773.mycocktails.Model.Database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
@Entity(foreignKeys = [ForeignKey(
        entity = Category::class,
        parentColumns = ["name"],
        childColumns = ["id"]
    )],
    indices = [Index(value = ["name"], unique = true)],
)

data class Cocktail(
    val name: String,
    val isAlcoholic: String,
    val glass: String,
    val instructions: String,
    val category: String,

    @PrimaryKey
    val id: Int
)//Implement Parcelable
