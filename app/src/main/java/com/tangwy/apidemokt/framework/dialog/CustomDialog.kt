package com.tangwy.apidemokt.framework.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment

class CustomDialog: DialogFragment() {

    companion object {

        private const val PARAM_RES_ID = "res_id"

        fun create(resId: Int): CustomDialog {
            val dialog = CustomDialog()
            val bundle = Bundle()
            bundle.putInt(PARAM_RES_ID, resId)
            dialog.arguments = bundle
            return dialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val resId = arguments!!.getInt(PARAM_RES_ID)
        val contentView = activity!!.layoutInflater.inflate(resId, null)
        return AlertDialog.Builder(activity)
                .setView(contentView)
                /* or setView(resId) */
                .create()
    }
}