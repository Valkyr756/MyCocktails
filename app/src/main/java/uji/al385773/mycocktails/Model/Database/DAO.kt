package uji.al385773.mycocktails.Model.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room

@Dao
interface DAO {
    @Insert
    fun insertCategories(categories: List<Category>)

    @Query("SELECT * FROM category ORDER BY name")
    fun getCategories(): List<Category>

    @Insert
    fun insertIngredients(ingredients: List<Ingredient>)

    @Query("SELECT * FROM ingredient ORDER BY name")
        fun getIngredients(): List<Ingredient>
}