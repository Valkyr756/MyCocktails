package uji.al385773.mycocktails.Search

import uji.al385773.mycocktails.Model.Database.Category
import uji.al385773.mycocktails.Model.Database.Ingredient
import uji.al385773.mycocktails.ResultsInfo
import java.util.*

interface ISearchView {
    fun showCategories(categories: List<Category>)
    fun showIngredients(ingredients: List<Ingredient>)
    fun errorMessage(message: String)
    fun spinnerSearchEnabled(enabled: Boolean)
    fun autoCompleteSearchEnabled(enabled: Boolean)
    fun startResultsActivity(resultsInfo: ResultsInfo)
}