package uji.al385773.mycocktails.Search

import uji.al385773.mycocktails.Model.Database.Category
import uji.al385773.mycocktails.Model.Database.Ingredient

class SearchPresenter(val view: ISearchView, val model: SearchModel) {

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
        //Falta implementar que se guarde el ingrediente para la busqueda posterior

        view.autoCompleteSearchEnabled(true)
        view.spinnerSearchEnabled(false)
    }

    fun setChosenCategory(category: Category) {
        //Falta implementar que se guarde la categoria para la busqueda posterior

        view.spinnerSearchEnabled(true)
        view.autoCompleteSearchEnabled(false)

    }
}