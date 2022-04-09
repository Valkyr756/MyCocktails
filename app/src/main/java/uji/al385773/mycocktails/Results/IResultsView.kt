package uji.al385773.mycocktails.Results

import uji.al385773.mycocktails.Model.Database.CocktailBundle

interface IResultsView {
    fun showCocktails(cocktailBundle: List<CocktailBundle>)
    fun errorMessage(message: String)
    fun startDetailsActivity(cocktailBundle: CocktailBundle)
}