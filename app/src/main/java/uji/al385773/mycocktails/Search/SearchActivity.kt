package uji.al385773.mycocktails.Search


import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import uji.al385773.mycocktails.Model.Database.Category
import uji.al385773.mycocktails.Model.Database.Ingredient
import uji.al385773.mycocktails.R

lateinit var spinner: Spinner
lateinit var autoCompleteIngredient: AutoCompleteTextView
lateinit var spinnerButton: Button
lateinit var autoCompleteButton: Button
lateinit var presenter:SearchPresenter

class SearchActivity : AppCompatActivity(), ISearchView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        spinner = findViewById(R.id.spinner)
        autoCompleteIngredient = findViewById(R.id.autoCompleteTextIngredient)

        presenter = SearchPresenter(this, SearchModel(this))


    }

    override fun showCategories(categories: List<Category>) {
        val adapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,categories)
        spinner.adapter = adapter
    }

    override fun showIngredients(ingredients: List<Ingredient>) {
        val adapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,ingredients)
        autoCompleteIngredient.setAdapter(adapter)
    }

    override fun errorMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}