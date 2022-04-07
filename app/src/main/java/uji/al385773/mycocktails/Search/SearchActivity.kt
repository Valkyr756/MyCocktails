package uji.al385773.mycocktails.Search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import uji.al385773.mycocktails.Model.Database.Category
import uji.al385773.mycocktails.Model.Database.Ingredient
import uji.al385773.mycocktails.R
import uji.al385773.mycocktails.Results.ResultsActivity
import uji.al385773.mycocktails.ResultsInfo

class SearchActivity : AppCompatActivity(), ISearchView, RadioGroup.OnCheckedChangeListener {

    lateinit var spinner: Spinner
    lateinit var autoCompleteIngredient: AutoCompleteTextView
    lateinit var spinnerButton: Button
    lateinit var autoCompleteButton: Button
    lateinit var radioGroup: RadioGroup
    lateinit var radioButtonInet: RadioButton
    lateinit var radioButtonLocal: RadioButton
    lateinit var presenter:SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        spinner = findViewById(R.id.spinner)
        autoCompleteIngredient = findViewById(R.id.autoCompleteTextIngredient)
        radioGroup = findViewById(R.id.radioGroup)
        radioButtonInet = findViewById(R.id.radioButtonInet)
        radioButtonLocal = findViewById(R.id.radioButtonLocal)
        spinnerButton = findViewById(R.id.searchSpinner)
        spinnerButton.setOnClickListener { presenter.doSearch() }
        autoCompleteButton = findViewById(R.id.searchAutoComplete)
        autoCompleteButton.setOnClickListener { presenter.doSearch() }

        presenter = SearchPresenter(this, Model(this))
    }

    override fun showCategories(categories: List<Category>) {
        val adapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,categories)
        spinner.adapter = adapter

        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val category : Category = spinner.getItemAtPosition(p2) as Category
                presenter.setChosenCategory(category)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }.also { spinner.onItemSelectedListener = it }
    }

    override fun showIngredients(ingredients: List<Ingredient>) {
        val adapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,ingredients)
        autoCompleteIngredient.setAdapter(adapter)

        autoCompleteIngredient.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                val ingredient = p0.toString()
                ingredients.binarySearch { it.name.compareTo(ingredient) }.let {
                    if (it >= 0)
                        presenter.setChosenIngredient(ingredients[it])
                }
            }
        })
    }

    override fun onCheckedChanged(p0: RadioGroup?, idRadio: Int) {
        when (idRadio){
            radioButtonInet.id -> presenter.setTypeOfSearch(true)
            radioButtonLocal.id-> presenter.setTypeOfSearch(false)
        }
    }

    override fun errorMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    override fun spinnerSearchEnabled(enabled: Boolean) {
        spinnerButton.isEnabled = enabled
    }

    override fun autoCompleteSearchEnabled(enabled: Boolean) {
        autoCompleteButton.isEnabled = enabled
    }

    override fun startResultsActivity(resultsInfo: ResultsInfo) {
        val intent = Intent(this, ResultsActivity::class.java).apply {
            putExtra(ResultsActivity.RESULTS_INFO, resultsInfo) }
        startActivity(intent)
    }
}