package uji.al385773.mycocktails.Results

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uji.al385773.mycocktails.Model.Database.Cocktail
import uji.al385773.mycocktails.Model.Database.CocktailBundle
import uji.al385773.mycocktails.R

class ResultsAdapter(private val results: List<CocktailBundle>,
                     private val listener: (Cocktail) -> Unit
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
            titleCocktailText.text = result.name
            categoryText.text = result.category
            alcoholicText.text = result.isAlcoholic
            ingredientsText.text = result.cocktailIngredients.joinToString(", ")
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }
}