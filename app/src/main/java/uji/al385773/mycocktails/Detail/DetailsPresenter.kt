package uji.al385773.mycocktails.Detail

import uji.al385773.mycocktails.Search.Model

class DetailsPresenter(val view: IDetailsView, val model: Model) {

    init {
        model.getImageBitmap(
            {
                view.fillCocktailInfo(DetailsActivity.cocktailInfo, it)
            },
            DetailsActivity.cocktailInfo.cocktail.imageUrl
        )

    }

    fun onRequestScore() = view.askForScore()

    fun onAddClicked() {
        model.addCocktailToDatabase(DetailsActivity.cocktailInfo)
        view.askForAdd()
    }

    fun onScoreChanged(score: Int) {
        DetailsActivity.cocktailInfo.cocktail.score = score
    }
}