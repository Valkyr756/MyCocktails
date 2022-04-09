package uji.al385773.mycocktails.Detail

import android.graphics.Bitmap
import uji.al385773.mycocktails.Model.Database.CocktailBundle

interface IDetailsView {
    fun askForScore()
    fun fillCocktailInfo(cocktailBundle: CocktailBundle)
    //fun saveBitmapFromImage(imageBitmap: Bitmap)
}