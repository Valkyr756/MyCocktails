package uji.al385773.mycocktails.Results

import uji.al385773.mycocktails.Search.Model

class ResultsPresenter(val view: IResultsView, val model: Model) {

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