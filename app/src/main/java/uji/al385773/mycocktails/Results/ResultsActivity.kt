package uji.al385773.mycocktails.Results

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uji.al385773.mycocktails.Model.Database.Cocktail
import uji.al385773.mycocktails.R
import uji.al385773.mycocktails.ResultsInfo
import uji.al385773.mycocktails.Search.Model

class ResultsActivity : AppCompatActivity(), IResultsView {

    private lateinit var cocktailView: RecyclerView
    private lateinit var presenter: ResultsPresenter
    private lateinit var progressBar: ProgressBar

    companion object {
        const val RESULTS_INFO = "ResultsInfo"
        lateinit var retrievedInfo: ResultsInfo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val resultsInfo: ResultsInfo = intent.getParcelableExtra(RESULTS_INFO)!!
        retrievedInfo = resultsInfo

        progressBar = findViewById(R.id.progressBar)
        cocktailView = findViewById(R.id.cocktailView)
        cocktailView.layoutManager = LinearLayoutManager(this)

        presenter = ResultsPresenter(this, Model(this))
    }

    override fun showCocktails(cocktails: List<Cocktail>) {
        cocktailView.adapter = ResultsAdapter(cocktails)
    }

    override fun errorMessage(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }
}