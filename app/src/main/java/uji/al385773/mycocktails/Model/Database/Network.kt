package uji.al385773.mycocktails.Model.Database

import android.content.Context
import com.android.volley.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uji.al385773.mycocktails.Model.Database.DAO

class Network private constructor(context: Context) {

    companion object : SingletonHolder<Network, Context>(::Network) {
    }

    fun getCategories(listener: Response.Listener<List<Category>>,
                      errorListener: Response.ErrorListener) =
        // Launch a coroutine
        GlobalScope.launch(Dispatchers.Main) {
            // Blocking call to read the categories from the DAO
            val categories = withContext(Dispatchers.IO) {
                database.dao.getCategories()
            }
            if (categories.isEmpty()) {
                // Recover categories from the net
                network.getCategories(Response.Listener {
                    // Launch a coroutine to store the
                    // categories in the database
                    GlobalScope.launch {
                        database.dao.insertCategories(it)
                    }
                    // Pass the categories to the listener
                    listener.onResponse(it)
                }, Response.ErrorListener {
                    errorListener.onErrorResponse(it)
                })
            }
            else
                // Pass the categories to the listener
                listener.onResponse(categories)
        }
}
    