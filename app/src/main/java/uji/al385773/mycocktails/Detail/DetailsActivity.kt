package uji.al385773.mycocktails.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import uji.al385773.mycocktails.Dialogs.ScoreDialog
import uji.al385773.mycocktails.Model.Database.CocktailBundle
import uji.al385773.mycocktails.R
import uji.al385773.mycocktails.Search.Model

class DetailsActivity : AppCompatActivity(), IDetailsView, ScoreDialog.ScoreListener {

    lateinit var nameCocktail: TextView
    lateinit var alcoholicCocktail: TextView
    lateinit var typeOfGlassCocktail: TextView
    lateinit var categoryCocktail: TextView
    lateinit var scoreCocktail: TextView
    lateinit var instructionsCocktail: TextView
    lateinit var ingredientsCocktail: TextView
    lateinit var scoreButton: Button
    lateinit var addButton: Button
    lateinit var cocktailPhoto: ImageView
    lateinit var presenter: DetailsPresenter

    //lateinit var imageBit: Bitmap

    companion object {
        const val DETAILS_INFO = "DetailsInfo"
        lateinit var cocktailInfo: CocktailBundle
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val detailsInfo: CocktailBundle = intent.getParcelableExtra(DETAILS_INFO)!!
        cocktailInfo = detailsInfo

        nameCocktail = findViewById(R.id.nameCocktailText)
        alcoholicCocktail = findViewById(R.id.alcoholicCocktailText)
        typeOfGlassCocktail = findViewById(R.id.glassCocktailText)
        categoryCocktail = findViewById(R.id.categoryCocktailText)
        scoreCocktail = findViewById(R.id.scoreCocktailText)
        instructionsCocktail = findViewById(R.id.instructionsCocktailText)
        ingredientsCocktail = findViewById(R.id.ingredientsCocktailText)
        cocktailPhoto = findViewById(R.id.cocktailPhoto)

        scoreButton = findViewById(R.id.scoreCocktailButton)
        scoreButton.setOnClickListener { presenter.onRequestScore() }
        addButton = findViewById(R.id.addCocktailButton)
        addButton.setOnClickListener { presenter.onAddClicked() }

        presenter = DetailsPresenter(this, Model(this))




    }

    override fun askForScore() = ScoreDialog().show(supportFragmentManager, "Score")

    override fun askForAdd() {
        AlertDialog.Builder(this).apply {
            setTitle("Database")
            setMessage("Cocktail and ingredients inserted into local database")
            setPositiveButton("OK") { _, _ ->
                finish()
            }
            show()
        }

    }
    /*override fun saveBitmapFromImage(imageBitmap: Bitmap) {
        imageBit = imageBitmap
    }*/

    override fun fillCocktailInfo(cocktailBundle: CocktailBundle) {
        nameCocktail.text = cocktailBundle.cocktail.name
        alcoholicCocktail.text = cocktailBundle.cocktail.isAlcoholic
        typeOfGlassCocktail.text = cocktailBundle.cocktail.glass
        categoryCocktail.text = cocktailBundle.cocktail.category
        instructionsCocktail.text = cocktailBundle.cocktail.instructions
        //cocktailPhoto.setImageBitmap(imageBit)

        var i = 0
        while (i < cocktailBundle.cocktailIngredients.size){
            if (cocktailBundle.cocktailIngredients[i].measures == "null")
                ingredientsCocktail.text = "${ingredientsCocktail.text}${cocktailBundle.cocktailIngredients[i].ingredientName}"
            else
                ingredientsCocktail.text = "${ingredientsCocktail.text}${cocktailBundle.cocktailIngredients[i].measures}${cocktailBundle.cocktailIngredients[i].ingredientName}"

            if (i != cocktailBundle.cocktailIngredients.size - 1)
                ingredientsCocktail.text = ingredientsCocktail.text.toString().plus(", ")
            i++
        }
    }

    override fun onScoreAvailable(score: Int) {
        scoreCocktail.text = "$score out of 10"
        presenter.onScoreChanged(score)
        addButton.isEnabled = true
    }
}