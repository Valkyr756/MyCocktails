package uji.al385773.mycocktails.Results

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uji.al385773.mycocktails.Model.Database.CocktailBundle
import uji.al385773.mycocktails.R

class ResultsAdapter(private val results: List<CocktailBundle>,
                     private val listener: (CocktailBundle) -> Unit
                     ): RecyclerView.Adapter<ResultsAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {   //inner como static

        var titleCocktailText: TextView = view.findViewById(R.id.titleCocktailText)
        var categoryText: TextView = view.findViewById(R.id.categoryText)
        var alcoholicText: TextView = view.findViewById(R.id.alcoholicText)
        var ingredientsText: TextView = view.findViewById(R.id.ingredientsText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.cocktail_line, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]

        with(holder) {
            itemView.setOnClickListener { listener(result) }
            titleCocktailText.text = result.cocktail.name
            categoryText.text = result.cocktail.category
            alcoholicText.text = result.cocktail.isAlcoholic

            var i = 0
            ingredientsText.text = ""   //Esto es necesario porque sino al arrastrar la pantalla se vuelve a cargar el RecyclerView y se añade otra cadena a la ya existente
            while (i < result.cocktailIngredients.size){
                if (result.cocktailIngredients[i].measures == "null")   //Algunos ingredientes no tienen mediciones (null)
                    ingredientsText.text = "${ingredientsText.text}${result.cocktailIngredients[i].ingredientName}"
                else
                    ingredientsText.text = "${ingredientsText.text}${result.cocktailIngredients[i].measures} ${result.cocktailIngredients[i].ingredientName}"

                if (i != result.cocktailIngredients.size - 1)
                    ingredientsText.text = ingredientsText.text.toString().plus(", ")
                i++
            }
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }
}