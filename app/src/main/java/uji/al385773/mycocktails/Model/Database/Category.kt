package uji.al385773.mycocktails.Model.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey
    val name: String
)

