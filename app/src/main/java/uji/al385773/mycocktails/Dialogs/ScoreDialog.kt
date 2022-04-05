package uji.al385773.mycocktails.Dialogs

import androidx.fragment.app.DialogFragment

class ScoreDialog: DialogFragment() {
    interface ScoreListener {
        fun onScoreAvailable(score: Int)
    }


}