package uji.al385773.mycocktails.Model.Database

import androidx.room.RoomDatabase
import androidx.room.Database

@Database(
    entities = [
        Category::class,
        Ingredient::class
    ],
    version = 1
)


abstract class AbstractDatabase: RoomDatabase() {
        abstract fun getDAO(): DAO
}
