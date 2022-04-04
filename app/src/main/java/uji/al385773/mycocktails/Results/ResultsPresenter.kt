package uji.al385773.mycocktails.Results

import uji.al385773.mycocktails.Model.Database.Cocktail
import uji.al385773.mycocktails.Search.Model

class ResultsPresenter(val view: IResultsView, val model: Model) {

    fun onCocktailDetailRequested(cocktail: Cocktail) {
        model.getDetails(cocktail)
        view.startDetailsActivity(model.detailsInfo)
    }

    init {
        model.getCocktails({
            view.showCocktails(it)
        },
            {
                view.errorMessage(it.toString())
            },
            ResultsActivity.retrievedInfo
        )
    }
}