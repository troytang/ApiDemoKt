package com.tangwy.apidemokt.util

import android.os.Build

class Device {

    companion object {
        fun afterO(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
                    || (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1 && Build.VERSION.PREVIEW_SDK_INT == 1)
        }
    }
}