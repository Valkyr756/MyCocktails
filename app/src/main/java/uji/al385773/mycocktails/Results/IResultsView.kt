package uji.al385773.mycocktails.Results

import uji.al385773.mycocktails.DetailsInfo
import uji.al385773.mycocktails.Model.Database.Cocktail
import uji.al385773.mycocktails.Model.Database.CocktailBundle

interface IResultsView {
    fun showCocktails(cocktails: List<CocktailBundle>)
    fun errorMessage(message: String)
    fun startDetailsActivity(detailsInfo: DetailsInfo)
}