package uji.al385773.mycocktails.Detail

import uji.al385773.mycocktails.Search.Model

class DetailsPresenter(val view: IDetailsView, val model: Model) {

    init {
        /*model.getImageBitmap(
            {
                view.saveBitmapFromImage(it)
            },
            DetailsActivity.cocktailInfo.cocktail.imageUrl
        )*/
        view.fillCocktailInfo(DetailsActivity.cocktailInfo)
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