package uji.al385773.mycocktails.Search


import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
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
        spinner = findViewById(R.id.spinner)


        presenter = SearchPresenter(this, SearchModel(this))


    }

    override fun showCategories(categories: List<Category>) {
        val adapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,categories)
        spinner.adapter = adapter
    }

    override fun showIngredients(ingredients: List<Ingredient>) {

    }

    override fun errorMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}