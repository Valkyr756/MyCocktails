package uji.al385773.mycocktails.Search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Spinner
import uji.al385773.mycocktails.Model.Database.Category
import uji.al385773.mycocktails.Model.Database.Ingredient
import uji.al385773.mycocktails.R

lateinit var spinner: Spinner
lateinit var autoCompleteTextView: AutoCompleteTextView
lateinit var spinnerButton: Button
lateinit var completeButton: Button
lateinit var presenter:SearchPresenter

class SearchActivity : AppCompatActivity(), ISearchView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


    }

    override fun showCategories(categories: List<Category>) {

    }

    override fun showIngredients(ingredients: List<Ingredient>) {

    }
}