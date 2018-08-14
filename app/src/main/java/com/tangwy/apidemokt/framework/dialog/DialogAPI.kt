package com.tangwy.apidemokt.framework.dialog


class DialogAPI {

    companion object {

        fun createCommon(): MessageDialog {
            return MessageDialog.create()
        }

        fun createList(type: ListDialog.Type): ListDialog {
            return ListDialog.create(type)
        }

        fun createCustom(resId: Int): CustomDialog {
            return CustomDialog.create(resId)
        }

        fun createCallback(): CallbackDialog {
            return CallbackDialog.create()
        }

        fun createEmbedded(resId: Int): EmbeddedDialog {
            return EmbeddedDialog.create(resId)
        }
    }
}