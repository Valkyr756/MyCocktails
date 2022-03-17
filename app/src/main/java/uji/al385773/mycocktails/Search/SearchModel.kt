package uji.al385773.mycocktails.Search

import android.content.Context
import com.android.volley.Response
import uji.al385773.mycocktails.Model.Database.Category
import uji.al385773.mycocktails.Model.Database.Network

class SearchModel(context:Context) {
    val network = Network.getInstance(context)
    fun getCategories(
        listener: Response.Listener<List<Category>>,
        errorListener: Response.ErrorListener
    ) {
        network.getCategories(listener, errorListener)
    }

}
