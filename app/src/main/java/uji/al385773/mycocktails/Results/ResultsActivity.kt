package uji.al385773.mycocktails.Results

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uji.al385773.mycocktails.R
import uji.al385773.mycocktails.ResultsInfo

class ResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val resultsInfo: ResultsInfo = intent.getParcelableExtra(RESULTS_INFO)!!
    }

    companion object {
        const val RESULTS_INFO = "ResultsInfo"
    }
}