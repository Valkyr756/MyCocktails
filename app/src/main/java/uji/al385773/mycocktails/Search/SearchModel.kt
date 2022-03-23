package uji.al385773.mycocktails.Search

import android.content.Context
import com.android.volley.Response
import uji.al385773.mycocktails.Model.Database.Category
import uji.al385773.mycocktails.Model.Database.Ingredient
import uji.al385773.mycocktails.Model.Database.Network

class SearchModel(context:Context) {
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

}
