package uji.al385773.mycocktails.Search

import uji.al385773.mycocktails.Model.Database.Category
import uji.al385773.mycocktails.Model.Database.Ingredient

class SearchPresenter(val view: ISearchView, val model: Model) {

    init {
        model.getCategories({
            view.showCategories(it)
        },
            {
                view.errorMessage(it.toString())
            })

        model.getIngredients({
            view.showIngredients(it)
        },
            {
                view.errorMessage(it.toString())
            })
    }

    fun setChosenIngredient(ingredient: Ingredient) {
        model.setIngredient(ingredient.toString())

        view.autoCompleteSearchEnabled(true)
        view.spinnerSearchEnabled(false)
    }

    fun setChosenCategory(category: Category) {
        model.setCategory(category.toString())

        view.spinnerSearchEnabled(true)
        view.autoCompleteSearchEnabled(false)

    }

    fun doSearch() {
        view.startResultsActivity(model.resultsInfo)
    }
}