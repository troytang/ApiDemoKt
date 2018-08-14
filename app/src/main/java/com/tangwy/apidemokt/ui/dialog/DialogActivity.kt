package com.tangwy.apidemokt.ui.dialog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.tangwy.apidemokt.framework.dialog.DialogAPI
import com.tangwy.apidemokt.R
import com.tangwy.apidemokt.framework.dialog.CallbackDialog
import com.tangwy.apidemokt.framework.dialog.ListDialog
import kotlinx.android.synthetic.main.content_dialog.*


class DialogActivity : AppCompatActivity(), CallbackDialog.Callback, View.OnClickListener {

    override fun onClick(v: View?) {
        when (v) {
            common -> DialogAPI.createCommon().show(supportFragmentManager, "Common")
            single -> DialogAPI.createList(ListDialog.Type.Single).show(supportFragmentManager, "Single")
            single_permanent -> DialogAPI.createList(ListDialog.Type.SinglePermanent).show(supportFragmentManager, "Single Permanent")
            multi_permanent -> DialogAPI.createList(ListDialog.Type.MultiPermanent).show(supportFragmentManager, "Multi Permanent")
            custom -> DialogAPI.createCustom(R.layout.dialog_signin).show(supportFragmentManager, "Custom")
            callback -> DialogAPI.createCallback().show(supportFragmentManager, "Callback")
            embedded -> DialogAPI.createEmbedded(R.layout.dialog_signin).show(supportFragmentManager, "Embedded")
        }
    }

    override fun onPositiveClick() {
        Toast.makeText(this, "onPositiveClick Callback from CallbackDialog", Toast.LENGTH_SHORT).show()
    }

    override fun onNegativeClick() {
        Toast.makeText(this, "onNegativeClick Callback from CallbackDialog", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        common.setOnClickListener(this)
        single.setOnClickListener(this)
        single_permanent.setOnClickListener(this)
        multi_permanent.setOnClickListener(this)
        custom.setOnClickListener(this)
        callback.setOnClickListener(this)
        embedded.setOnClickListener(this)
    }
}