package com.tangwy.apidemokt.ui.dialog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.tangwy.apidemokt.framework.dialog.DialogAPI
import com.tangwy.apidemokt.R
import com.tangwy.apidemokt.framework.dialog.CallbackDialog
import com.tangwy.apidemokt.framework.dialog.ListDialog
import kotlinx.android.synthetic.main.content_dialog.*


class DialogActivity: AppCompatActivity(), CallbackDialog.Callback {

    override fun onPositiveClick() {
        Toast.makeText(this, "onPositiveClick Callback from CallbackDialog", Toast.LENGTH_SHORT).show()
    }

    override fun onNegativeClick() {
        Toast.makeText(this, "onNegativeClick Callback from CallbackDialog", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        common.setOnClickListener {
            showCommon()
        }

        single.setOnClickListener {
            showSingleList()
        }

        single_permanent.setOnClickListener {
            showSinglePermanentList()
        }

        multi_permanent.setOnClickListener {
            showMultiPermanentList()
        }

        custom.setOnClickListener {
            showCustom()
        }

        callback.setOnClickListener {
            showCallback()
        }

        embedded.setOnClickListener {
            showEmbedded()
        }
    }

    private fun showCommon() {
        DialogAPI.createCommon().show(supportFragmentManager, "Common")
    }

    private fun showSingleList() {
        DialogAPI.createList(ListDialog.Type.Single).show(supportFragmentManager, "Single")
    }

    private fun showSinglePermanentList() {
        DialogAPI.createList(ListDialog.Type.SinglePermanent).show(supportFragmentManager, "Single Permanent")
    }

    private fun showMultiPermanentList() {
        DialogAPI.createList(ListDialog.Type.MultiPermanent).show(supportFragmentManager, "Multi Permanent")
    }

    private fun showCustom() {
        DialogAPI.createCustom(R.layout.dialog_signin).show(supportFragmentManager, "Custom")
    }

    private fun showCallback() {
        DialogAPI.createCallback().show(supportFragmentManager, "Callback")
    }

    private fun showEmbedded() {
        DialogAPI.createEmbedded(R.layout.dialog_signin).show(supportFragmentManager, "Embedded")
    }
}