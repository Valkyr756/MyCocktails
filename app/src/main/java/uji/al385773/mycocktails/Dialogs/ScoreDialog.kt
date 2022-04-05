package uji.al385773.mycocktails.Dialogs

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import androidx.fragment.app.DialogFragment
import uji.al385773.mycocktails.R
import java.lang.IllegalStateException

class ScoreDialog: DialogFragment() {
    interface ScoreListener {
        fun onScoreAvailable(score: Int)
    }

    private lateinit var scoreBar: RatingBar

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity: Activity = activity ?: throw IllegalStateException("Activity cannot be null")
        val view: View = activity.layoutInflater.inflate(R.layout.dialog_score, null)
        with (view) {

        }

        //return super.onCreateDialog(savedInstanceState)
    }
}