package uji.al385773.mycocktails.Search

import android.content.Context
import com.android.volley.Response
import uji.al385773.mycocktails.DetailsInfo
import uji.al385773.mycocktails.Model.Database.*
import uji.al385773.mycocktails.ResultsInfo

class Model(context:Context) {
    var possibleIngredient: String = ""
    var possibleCategory: String = ""
    var isCategory: Boolean = true

    var detailsName: String = ""
    var detailsalcoholic: String = ""
    var detailsGlass: String = ""
    var detailsCategory: String = ""
    var detailsInstructions: String = ""
    var detailsIngredients: String = ""

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
        listener: Response.Listener<List<CocktailBundle>>,
        errorListener: Response.ErrorListener,
        gameInfo: ResultsInfo
    ) {
        if (gameInfo.isCategory)
            network.getCocktailsByCategory({ getCocktailsFromID(it, listener, errorListener) }, errorListener, gameInfo.category)
        else
            network.getCocktailsByIngredient({ getCocktailsFromID(it, listener, errorListener) }, errorListener, gameInfo.ingredient)
    }

    private fun getCocktailsFromID(cocktailsID: List<String>, listener: Response.Listener<List<CocktailBundle>>, errorListener: Response.ErrorListener) {
        val cocktailBundle = ArrayList<CocktailBundle>()

        for (id in cocktailsID) {
            network.getCocktailByID({ cocktailBundle.add(it)
                                      if (cocktailBundle.size == cocktailsID.size){
                                          listener.onResponse(cocktailBundle)
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

    /*fun getDetails(cocktailBundle: CocktailBundle) {
        detailsName = cocktailBundle.cocktail.name
        detailsalcoholic = cocktailBundle.cocktail.isAlcoholic
        detailsGlass = cocktailBundle.cocktail.glass
        detailsCategory = cocktailBundle.cocktail.category
        detailsInstructions = cocktailBundle.cocktail.instructions
        detailsIngredients = cocktailBundle.cocktailIngredients.
    }*/

    val resultsInfo get() = ResultsInfo(possibleCategory,possibleIngredient, isCategory)
    //val detailsInfo get() = DetailsInfo(detailsName, detailsalcoholic, detailsGlass, detailsCategory, detailsInstructions, detailsIngredients)
}
