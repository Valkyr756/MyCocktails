package uji.al385773.mycocktails.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text
import uji.al385773.mycocktails.DetailsInfo
import uji.al385773.mycocktails.Model.Database.Cocktail
import uji.al385773.mycocktails.R
import uji.al385773.mycocktails.Results.ResultsActivity
import uji.al385773.mycocktails.ResultsInfo

class DetailsActivity : AppCompatActivity() {

    lateinit var nameCocktail: TextView
    lateinit var alcoholicCocktail: TextView
    lateinit var typeOfGlassCocktail: TextView
    lateinit var categoryCocktail: TextView
    lateinit var scoreCocktail: TextView
    lateinit var instructionsCocktail: TextView
    lateinit var ingredientsCocktail: TextView

    companion object {
        const val DETAILS_INFO = "DetailsInfo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        nameCocktail = findViewById(R.id.nameCocktailText)
        alcoholicCocktail = findViewById(R.id.alcoholicCocktailText)
        typeOfGlassCocktail = findViewById(R.id.glassCocktailText)
        categoryCocktail = findViewById(R.id.categoryCocktailText)
        scoreCocktail = findViewById(R.id.scoreCocktailText)
        instructionsCocktail = findViewById(R.id.instructionsCocktailText)
        ingredientsCocktail = findViewById(R.id.ingredientsCocktailText)

        val detailsInfo: DetailsInfo = intent.getParcelableExtra(DETAILS_INFO)!!

        nameCocktail.text = detailsInfo.name
        alcoholicCocktail.text = detailsInfo.alcoholic
        typeOfGlassCocktail.text = detailsInfo.glass
        categoryCocktail.text = detailsInfo.category
        instructionsCocktail.text = detailsInfo.instructions
        ingredientsCocktail.text = detailsInfo.ingredients
    }
}