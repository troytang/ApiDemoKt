package com.tangwy.apidemokt.framework.dialog

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window

class EmbeddedDialog: DialogFragment() {

    companion object {

        private const val PARAM_RES_ID = "res_id"

        fun create(resId: Int): EmbeddedDialog {
            val dialog = EmbeddedDialog()
            val bundle = Bundle()
            bundle.putInt(PARAM_RES_ID, resId)
            dialog.arguments = bundle
            return dialog
        }
    }

    /**
     * 由此创建视图
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val resId = arguments!!.getInt(PARAM_RES_ID)
        return activity?.layoutInflater?.inflate(resId, null)
    }

    /**
     * 当作为 Dialog 的时候调用
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }
}