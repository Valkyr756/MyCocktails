package uji.al385773.mycocktails.Search

import android.content.Context
import com.android.volley.Response
import uji.al385773.mycocktails.Model.Database.Category
import uji.al385773.mycocktails.Model.Database.Cocktail
import uji.al385773.mycocktails.Model.Database.Ingredient
import uji.al385773.mycocktails.Model.Database.Network
import uji.al385773.mycocktails.ResultsInfo

class Model(context:Context) {
    var possibleIngredient: String = ""
    var possibleCategory: String = ""
    var isCategory: Boolean = true

    private val network = Network.getInstance(context)

    fun getCategories(
        listener: Response.Listener<List<Category>>,
        errorListener: Response.ErrorListener
    ) {
        network.getCategories(listener, errorListener)
    }

    fun getIngredients(
        listener: Response.Listener<List<Ingredient>>,
        errorListener: Response.ErrorListener
    ) {
        network.getIngredients(listener, errorListener)
    }

    fun getCocktails(
        listener: Response.Listener<List<Cocktail>>,
        errorListener: Response.ErrorListener,
        gameInfo: ResultsInfo
    ) {
        if (gameInfo.isCategory)
            network.getCocktailsByCategory({ getCocktailsFromID(it, listener, errorListener) }, errorListener, gameInfo.category)
        else
            network.getCocktailsByIngredient({ getCocktailsFromID(it, listener, errorListener) }, errorListener, gameInfo.ingredient)
    }

    private fun getCocktailsFromID(cocktailsID: List<String>, listener: Response.Listener<List<Cocktail>>, errorListener: Response.ErrorListener) {
        val cocktailList = ArrayList<Cocktail>()

        for (id in cocktailsID) {
            network.getCocktailByID({ cocktailList.add(it)
                                      if (cocktailList.size == cocktailsID.size){
                                          listener.onResponse(cocktailList)
                                      }
                                    }, errorListener, id)
        }


    }

    fun setIngredient(ingredient: String) {
        possibleIngredient = ingredient
        isCategory = false
    }

    fun setCategory(category: String) {
        possibleCategory = category
        isCategory = true
    }

    val resultsInfo get() = ResultsInfo(possibleCategory,possibleIngredient, isCategory)
}
