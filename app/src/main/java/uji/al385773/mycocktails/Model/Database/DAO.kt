package uji.al385773.mycocktails.Model.Database

import androidx.room.*

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCategories(categories: List<Category>)

    @Query("SELECT * FROM category ORDER BY name")
    fun getCategories(): List<Category>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIngredients(ingredients: List<Ingredient>)

    @Query("SELECT * FROM ingredient ORDER BY name")
        fun getIngredients(): List<Ingredient>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCocktail(cocktail: Cocktail)

    @Query("SELECT id FROM cocktail WHERE category == :cat")
        fun getCocktailsIDByCategory(cat: String): List<Int>

    @Query("SELECT cocktailID FROM cocktailIngredient WHERE ingredientName == :ingred")
        fun getCocktailsIDByIngredient(ingred: String): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCocktailIngredients(cocktailIngredients: List<CocktailIngredient>)

    @Query("SELECT * FROM cocktail WHERE id == :searchID ORDER BY cocktail.name")
        fun getCocktailByID(searchID: Int): Cocktail

    @Query("SELECT * FROM cocktailIngredient WHERE cocktailID == :searchID")
        fun getCocktailIngredientsByID(searchID: Int): List<CocktailIngredient>
}