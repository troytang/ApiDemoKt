package com.tangwy.apidemokt.framework.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog

class CallbackDialog : DialogFragment() {

    interface Callback {

        fun onPositiveClick()

        fun onNegativeClick()
    }

    companion object {

        fun create(): CallbackDialog {
            return CallbackDialog()
        }
    }

    var mCallback: Callback? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            mCallback = context as Callback
        } catch (e: Exception) {
            throw RuntimeException(context.toString() + "must implement NoticeDialogListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(this.activity!!)
                .setTitle("Title")
                .setMessage("Callback Dialog")
                .setPositiveButton("OK") { _, _ ->
                    mCallback?.onPositiveClick()
                }
                .setNegativeButton("Cancel") { _, _ ->
                    mCallback?.onNegativeClick()
                }
                .create()
    }
}