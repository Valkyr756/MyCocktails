package uji.al385773.mycocktails.Search

import android.content.Context
import com.android.volley.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uji.al385773.mycocktails.DetailsInfo
import uji.al385773.mycocktails.Model.Database.*
import uji.al385773.mycocktails.ResultsInfo

class Model(context:Context) {
    var possibleIngredient: String = ""
    var possibleCategory: String = ""
    var isCategory: Boolean = true

    private val network = Network.getInstance(context)
    private val database = Database.getInstance(context)

    fun getCategories(listener: Response.Listener<List<Category>>,
                      errorListener: Response.ErrorListener) = GlobalScope.launch(Dispatchers.Main) {
            val categories = withContext(Dispatchers.IO) {
                database.dao.getCategories()
            }
            if (categories.isEmpty()) {
                network.getCategories(Response.Listener {
                    GlobalScope.launch {
                        database.dao.insertCategories(it)
                    }
                    listener.onResponse(it)
                }, Response.ErrorListener {
                    errorListener.onErrorResponse(it)
                })
            }
            else
                listener.onResponse(categories)
    }

    fun getIngredients(listener: Response.Listener<List<Ingredient>>,
                      errorListener: Response.ErrorListener) = GlobalScope.launch(Dispatchers.Main) {
        val ingredients = withContext(Dispatchers.IO) {
            database.dao.getIngredients()
        }
        if (ingredients.isEmpty()) {
            network.getIngredients(Response.Listener {
                GlobalScope.launch {
                    database.dao.insertIngredients(it)
                }
                listener.onResponse(it)
            }, Response.ErrorListener {
                errorListener.onErrorResponse(it)
            })
        }
        else
            listener.onResponse(ingredients)
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

    val resultsInfo get() = ResultsInfo(possibleCategory,possibleIngredient, isCategory)
}
