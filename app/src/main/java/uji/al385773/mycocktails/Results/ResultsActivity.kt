package uji.al385773.mycocktails.Results

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uji.al385773.mycocktails.Model.Database.Cocktail
import uji.al385773.mycocktails.R
import uji.al385773.mycocktails.ResultsInfo
import uji.al385773.mycocktails.Search.Model

class ResultsActivity : AppCompatActivity(), IResultsView {

    private lateinit var CocktailView: RecyclerView
    private lateinit var presenter: ResultsPresenter
    private lateinit var progressBar: ProgressBar

    companion object {
        const val RESULTS_INFO = "ResultsInfo"
        lateinit var categoryRetrieved: String
        lateinit var ingredientRetrieved: String
        var choiceRetrieved: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val resultsInfo: ResultsInfo = intent.getParcelableExtra(RESULTS_INFO)!!
        categoryRetrieved = resultsInfo.category
        ingredientRetrieved = resultsInfo.ingredient
        choiceRetrieved = resultsInfo.searchChoice

        progressBar = findViewById(R.id.progressBar)
        CocktailView = findViewById(R.id.cocktailView)
        CocktailView.layoutManager = LinearLayoutManager(this)

        presenter = ResultsPresenter(this, Model(this))
    }

    override fun showCocktails(cocktails: List<Cocktail>) {
        TODO("Not yet implemented")
    }

    override fun errorMessage(message: String) {
        TODO("Not yet implemented")
    }
}