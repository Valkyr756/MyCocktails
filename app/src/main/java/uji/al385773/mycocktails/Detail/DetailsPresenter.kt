package uji.al385773.mycocktails.Detail

import uji.al385773.mycocktails.Search.Model

class DetailsPresenter(val view: IDetailsView, val model: Model) {
    fun onRequestScore() = view.askForScore()
}