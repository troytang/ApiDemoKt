package com.tangwy.apidemokt.framework.dialog

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.tangwy.apidemokt.R

class ListDialog : DialogFragment() {

    companion object {

        private const val PARAM_TYPE = "type"

        fun create(type: Type): ListDialog {
            val dialog = ListDialog()
            val bundle = Bundle()
            bundle.putSerializable(PARAM_TYPE, type)
            dialog.arguments = bundle
            return dialog
        }
    }

    enum class Type {
        Single, SinglePermanent, MultiPermanent
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val type = arguments!!.getSerializable(PARAM_TYPE)
        val selectedItems = mutableListOf<Int>()
        val builder = AlertDialog.Builder(this.activity!!)
                .setTitle("List")
        when(type) {
            Type.Single -> {
                builder.setItems(R.array.country) {_, which ->
                    Toast.makeText(activity, "Click ${resources.getStringArray(R.array.country)[which]}",
                            Toast.LENGTH_SHORT).show()
                }
            }

            Type.SinglePermanent -> {
                builder.setSingleChoiceItems(R.array.country, -1) { _, which ->
                    Toast.makeText(activity, "Choose ${resources.getStringArray(R.array.country)[which]}",
                            Toast.LENGTH_SHORT).show()
                }

                builder.setPositiveButton("OK") {dialog, which -> }

                builder.setNegativeButton("Cancel") {dialog, which ->  }
            }

            Type.MultiPermanent -> {
                builder.setMultiChoiceItems(R.array.country, null) { _, which, isChecked ->
                    if (isChecked) {
                        selectedItems.add(which)
                    } else if (selectedItems.contains(which)) {
                        selectedItems.remove(which)
                    }
                }
                builder.setPositiveButton("OK") {_, _ ->
                    val array = resources.getStringArray(R.array.country)
                    var str = ""
                    for (item in selectedItems) {
                        str += array[item]
                    }
                    Toast.makeText(activity, str, Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("Cancel") {dialog, which -> }
            }
        }
        return builder.create()
    }
}