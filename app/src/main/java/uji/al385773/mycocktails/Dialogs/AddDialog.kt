package uji.al385773.mycocktails.Dialogs

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import uji.al385773.mycocktails.R
import java.lang.IllegalStateException

class AddDialog: DialogFragment() {
    interface AddListener {
        fun onOkAddClicked()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity: Activity = activity ?: throw IllegalStateException("Activity cannot be null")
        val view: View = activity.layoutInflater.inflate(R.layout.dialog_add, null)

        return AlertDialog.Builder(activity).run {
            setView(view)
            setPositiveButton(android.R.string.ok) { _, _ -> onAddReady() }
            create()
        }
    }

    private fun onAddReady() {
        val listener = activity as AddListener?

        listener!!.onOkAddClicked()
    }
}