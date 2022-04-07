package uji.al385773.mycocktails.Results

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uji.al385773.mycocktails.Detail.DetailsActivity
import uji.al385773.mycocktails.Model.Database.CocktailBundle
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
//Guardar lo de local/inet del radiogroup igual que se haría con los ingredientes cuando los eliges, en una variable. Esa variable
    //la pasas al parcleable

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

    override fun showCocktails(cocktailBundle: List<CocktailBundle>) {
        cocktailView.adapter = ResultsAdapter(cocktailBundle) { presenter.onCocktailDetailRequested(it) }
        progressBar.isVisible = false
    }

    override fun errorMessage(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }

    override fun startDetailsActivity(cocktailBundle: CocktailBundle) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.DETAILS_INFO, cocktailBundle) }
        startActivity(intent)
    }
}