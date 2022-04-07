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

    @Query("SELECT * FROM cocktail WHERE category == :cat ORDER BY name")
        fun getCocktailsByCategory(cat: String): List<Cocktail>
//primero no hace falta lef join porque tienes el id de cocktails, luego sí se hace cuando coges los ingredientes del cocktail
    @Query("SELECT cocktail.name, cocktail.isAlcoholic, cocktail.glass, cocktail.instructions, cocktail.category, cocktail.id " +
            "FROM cocktail LEFT JOIN cocktailIngredient ON cocktail.id = cocktailIngredient.cocktailID " +
            "WHERE cocktailIngredient.ingredientName == :ingred ORDER BY cocktail.name")
        fun getCocktailsByIngredient(ingred: String): List<Cocktail>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCocktailIngredients(cocktailIngredients: List<CocktailIngredient>)

    @Query("SELECT cocktailIngredient.cocktailID, cocktailIngredient.measures, cocktailIngredient.ingredientName " +
            "FROM cocktailIngredient LEFT JOIN cocktail ON cocktailIngredient.cocktailID = cocktail.id " +
            "WHERE cocktail.id == :searchID")
        fun getCocktailIngredients(searchID: Int): List<CocktailIngredient>
}