package com.tangwy.apidemokt.framework.dialog

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.widget.Toast

class MessageDialog : DialogFragment() {

    companion object {
        fun create(): MessageDialog {
            return MessageDialog()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(this.activity!!)
                .setTitle("Title")
                .setMessage("Common Dialog")
                .setPositiveButton("Yes") { _, _ ->
                    Toast.makeText(context, "Click Yes", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No") { _, _ ->
                    Toast.makeText(context, "Click No", Toast.LENGTH_SHORT).show()
                }
                .create()
    }
}