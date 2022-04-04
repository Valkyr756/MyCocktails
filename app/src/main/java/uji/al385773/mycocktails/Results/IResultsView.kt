package uji.al385773.mycocktails.Results

import uji.al385773.mycocktails.DetailsInfo
import uji.al385773.mycocktails.Model.Database.Cocktail

interface IResultsView {
    fun showCocktails(cocktails: List<Cocktail>)
    fun errorMessage(message: String)
    fun startDetailsActivity(detailsInfo: DetailsInfo)
}