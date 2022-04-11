package uji.al385773.mycocktails.Search

import android.content.Context
import android.graphics.Bitmap
import com.android.volley.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uji.al385773.mycocktails.Model.Database.*
import uji.al385773.mycocktails.ResultsInfo

class Model(context:Context) {
    var possibleIngredient: String = ""
    var possibleCategory: String = ""
    var isCategory: Boolean = true
    var isInetSearch: Boolean = true

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

    fun getCocktailsFromLocal(
        listener: Response.Listener<List<CocktailBundle>>,
        gameInfo: ResultsInfo
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            val bundle = mutableListOf<CocktailBundle>()
            withContext(Dispatchers.IO) {

                val cocktailsID: List<Int>
                if (gameInfo.isCategory) {
                    cocktailsID = database.dao.getCocktailsIDByCategory(gameInfo.category)
                }
                else
                    cocktailsID = database.dao.getCocktailsIDByIngredient(gameInfo.ingredient)


                var cocktail: Cocktail
                var cocktailIngredients: List<CocktailIngredient>
                for (id in cocktailsID){
                    cocktail = database.dao.getCocktailByID(id)
                    cocktailIngredients = database.dao.getCocktailIngredientsByID(id)
                    bundle.add(CocktailBundle(cocktail, cocktailIngredients))
                }
            }
            listener.onResponse(bundle)
        }
    }

    /*fun getCocktailsFromLocal(
        listener: Response.Listener<List<CocktailBundle>>,
        gameInfo: ResultsInfo
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            val cocktailsID = withContext(Dispatchers.IO) {
                if (gameInfo.isCategory)
                    database.dao.getCocktailsIDByCategory(gameInfo.category)
                else
                    database.dao.getCocktailsIDByIngredient(gameInfo.ingredient)
            }
            val bundle = mutableListOf<CocktailBundle>()
            for (id in cocktailsID) {
                GlobalScope.launch {
                    val cocktail = withContext(Dispatchers.IO) {
                        database.dao.getCocktailByID(id)
                    }
                    GlobalScope.launch {
                        withContext(Dispatchers.IO) {
                            val cocktailIngredients = withContext(Dispatchers.IO) {
                                database.dao.getCocktailIngredientsByID(id)
                            }
                            bundle.add(CocktailBundle(cocktail, cocktailIngredients))
                        }
                    }
                }
            }
            listener.onResponse(bundle)
        }
    }*/

    fun getCocktailsFromInet(
        listener: Response.Listener<List<CocktailBundle>>,
        errorListener: Response.ErrorListener,
        gameInfo: ResultsInfo
    ) {
        if (gameInfo.isCategory)
            network.getCocktailsByCategory({ getCocktailsFromIDInet(it, listener, errorListener) }, errorListener, gameInfo.category)
        else
            network.getCocktailsByIngredient({ getCocktailsFromIDInet(it, listener, errorListener) }, errorListener, gameInfo.ingredient)
    }

    private fun getCocktailsFromIDInet(cocktailsID: List<String>, listener: Response.Listener<List<CocktailBundle>>, errorListener: Response.ErrorListener) {
        val cocktailBundle = ArrayList<CocktailBundle>()

        for (id in cocktailsID) {
            network.getCocktailByID({ cocktailBundle.add(it)
                                      if (cocktailBundle.size == cocktailsID.size){
                                          listener.onResponse(cocktailBundle)
                                      }
                                    }, errorListener, id)
        }
    }

    fun getImageBitmap(listener: Response.Listener<Bitmap>, imageUrl: String) {
        var bitmap: Bitmap
        network.getBitmapFromUrl({bitmap = it
                                  listener.onResponse(bitmap)
                                 },
                                 imageUrl)
    }

    fun setIngredient(ingredient: String) {
        possibleIngredient = ingredient
        isCategory = false
    }

    fun setCategory(category: String) {
        possibleCategory = category
        isCategory = true
    }

    fun setTypeOfSearch(isInet: Boolean) {
        isInetSearch = isInet
    }

    fun addCocktailToDatabase(cocktailInfo: CocktailBundle) {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO){
                database.dao.insertCocktail(cocktailInfo.cocktail)

                for (i in 0 until cocktailInfo.cocktailIngredients.size){
                    database.dao.insertCocktailIngredients(cocktailInfo.cocktailIngredients[i])
                }
            }
        }
        /*GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                database.dao.insertCocktailIngredients(cocktailInfo.cocktailIngredients)
            }
        }*/
    }

    val resultsInfo get() = ResultsInfo(possibleCategory,possibleIngredient, isCategory, isInetSearch)
}
