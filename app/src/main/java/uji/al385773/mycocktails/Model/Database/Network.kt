package uji.al385773.mycocktails.Model.Database

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class Network private constructor(context: Context) {

    companion object : SingletonHolder<Network, Context>(::Network)

    private val queue = Volley.newRequestQueue(context)

    private val LIST_LABEL = "drinks"
    private val CATEGORY_NAME_LABEL = "strCategory"
    private val INGREDIENT_NAME_LABEL = "strIngredient1"
    private val MEASURE_NAME_LABEL = "strMeasure1"
    private val COCKTAIL_ID_LABEL = "idDrink"
    private val COCKTAIL_NAME_LABEL = "strDrink"
    private val COCKTAIL_IS_ALCOHOLIC_LABEL = "strAlcoholic"
    private val COCKTAIL_INSTRUCTIONS_LABEL = "strInstructions"
    private val COCKTAIL_GLASS_LABEL = "strGlass"

    fun getCategories(listener: Response.Listener<List<Category>>, errorListener: Response.ErrorListener) {
        val url = "https://www.thecocktaildb.com/api/json/v1/1/list.php?c=list"

        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response -> processCategories(response, listener, errorListener) },
            { error -> errorListener.onErrorResponse(error) }
        )
        queue.add(request)
    }
    private fun processCategories(response: JSONObject, listener: Response.Listener<List<Category>>, errorListener: Response.ErrorListener) {
        val categories = ArrayList<Category>()
        try {
            val categoryArray = response.getJSONArray(LIST_LABEL)
            for (i in 0 until categoryArray.length()) {
                val categoryObject = categoryArray[i] as JSONObject
                val name = categoryObject.getString(CATEGORY_NAME_LABEL)
                categories.add(Category(name))
            }
        } catch (e: JSONException) {
            errorListener.onErrorResponse(VolleyError("BAD JSON FORMAT"))
            return
        }
        categories.sortBy { it.name }
        listener.onResponse(categories)
    }

    fun getIngredients(listener: Response.Listener<List<Ingredient>>, errorListener: Response.ErrorListener) {
        val url = "https://www.thecocktaildb.com/api/json/v1/1/list.php?i=list"

        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response -> processIngredients(response, listener, errorListener) },
            { error -> errorListener.onErrorResponse(error) }
        )
        queue.add(request)
    }
    private fun processIngredients(response: JSONObject, listener: Response.Listener<List<Ingredient>>, errorListener: Response.ErrorListener) {
        val ingredients = ArrayList<Ingredient>()
        try {
            val ingredientArray = response.getJSONArray(LIST_LABEL)
            for (i in 0 until ingredientArray.length()) {
                val ingredientObject = ingredientArray[i] as JSONObject
                val name = ingredientObject.getString(INGREDIENT_NAME_LABEL)
                ingredients.add(Ingredient(name))
            }
        } catch (e: JSONException) {
            errorListener.onErrorResponse(VolleyError("BAD JSON FORMAT"))
            return
        }
        ingredients.sortBy { it.name }
        listener.onResponse(ingredients)
    }

    fun getCocktailsByCategory(listener: Response.Listener<List<String>>, errorListener: Response.ErrorListener, category: String) {
        val url = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=$category"

        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response -> processCocktailsBySearchCriteria(response, listener, errorListener) },
            { error -> errorListener.onErrorResponse(error) }
        )
        queue.add(request)
    }

    fun getCocktailsByIngredient(listener: Response.Listener<List<String>>, errorListener: Response.ErrorListener, ingredient: String) {
        val url = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=$ingredient"

        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response -> processCocktailsBySearchCriteria(response, listener, errorListener) },
            { error -> errorListener.onErrorResponse(error) }
        )
        queue.add(request)
    }

    private fun processCocktailsBySearchCriteria(response: JSONObject, listener: Response.Listener<List<String>>, errorListener: Response.ErrorListener) {
        val cocktailsID = ArrayList<String>()
        try {
            val cocktailArray = response.getJSONArray(LIST_LABEL)
            for (i in 0 until cocktailArray.length()) {
                val cocktailObject = cocktailArray[i] as JSONObject
                val id = cocktailObject.getString(COCKTAIL_ID_LABEL)
                cocktailsID.add(id)
            }
        } catch (e: JSONException) {
            errorListener.onErrorResponse(VolleyError("BAD JSON FORMAT"))
            return
        }
        listener.onResponse(cocktailsID)
    }



    fun getCocktailByID(listener: Response.Listener<CocktailBundle>, errorListener: Response.ErrorListener, searchID: String) {
        val url = "https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=$searchID"

        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response -> processCocktailByID(response, listener, errorListener) },
            { error -> errorListener.onErrorResponse(error) }
        )
        queue.add(request)
    }

        private fun processCocktailByID(response: JSONObject, listener: Response.Listener<CocktailBundle>, errorListener: Response.ErrorListener) {
        try {
            val cocktailArray = response.getJSONArray(LIST_LABEL)
            val cocktailObject = cocktailArray[0] as JSONObject

            val name = cocktailObject.getString(COCKTAIL_NAME_LABEL)
            val isAlcoholic = cocktailObject.getString(COCKTAIL_IS_ALCOHOLIC_LABEL)
            val instructions = cocktailObject.getString(COCKTAIL_INSTRUCTIONS_LABEL)
            val glass = cocktailObject.getString(COCKTAIL_GLASS_LABEL)
            val category = cocktailObject.getString(CATEGORY_NAME_LABEL)
            val id = cocktailObject.getString(COCKTAIL_ID_LABEL)
            val ingredientList = mutableListOf<CocktailIngredient>()

            var strIngredient: String = cocktailObject.getString(INGREDIENT_NAME_LABEL)    //strIngredient1
            var strMeasure: String = cocktailObject.getString(MEASURE_NAME_LABEL)  //strMeasure1
            var i = 1   //index for moving through the ingredients in the JSON

            while (strIngredient != "null") {   //Sirve tanto strIngredient como strMeasure
                ingredientList.add(CocktailIngredient(id.toInt(), strMeasure, strIngredient))
                i++
                strMeasure = cocktailObject.getString("strMeasure$i")
                strIngredient = cocktailObject.getString("strIngredient$i")
            }

            listener.onResponse(CocktailBundle(Cocktail(name, isAlcoholic, glass, instructions, category, id.toInt()), ingredientList))
        } catch (e: JSONException) {
            errorListener.onErrorResponse(VolleyError("BAD JSON FORMAT"))
        }
    }
}
