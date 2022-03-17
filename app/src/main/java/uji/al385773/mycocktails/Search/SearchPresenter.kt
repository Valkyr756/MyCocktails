package uji.al385773.mycocktails.Search

class SearchPresenter(val view: ISearchView, val model: SearchModel) {

    init {
        model.getCategories({
                            view.showCategories(it)
        },
            {
            view.errorMessage(it.toString())
        })

    }
}