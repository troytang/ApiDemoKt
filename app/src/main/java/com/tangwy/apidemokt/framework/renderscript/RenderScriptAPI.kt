package com.tangwy.apidemokt.framework.renderscript

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import com.tangwy.apidemokt.ScriptC_singlesource

class RenderScriptAPI {

    companion object {

        fun blur(context: Context, bitmap: Bitmap): Bitmap {
            val targetBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)

            val rs = RenderScript.create(context)
            val script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
            val inputAllocation = Allocation.createFromBitmap(rs, bitmap)
            val outputAllocation = Allocation.createFromBitmap(rs, targetBitmap)

            script.setRadius(25f)
            script.setInput(inputAllocation)
            script.forEach(outputAllocation)
            outputAllocation.copyTo(targetBitmap)

            rs.destroy()
            inputAllocation.destroy()
            outputAllocation.destroy()

            return targetBitmap
        }

        fun invert(context: Context, bitmap: Bitmap): Bitmap {
            val targetBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)

            // 最好全局只有一个 RS 对象
            val rs = RenderScript.create(context)
            val script = ScriptC_singlesource(rs)
            val inputAllocation = Allocation.createFromBitmap(rs, bitmap)
            val outputAllocation = Allocation.createFromBitmap(rs, targetBitmap)

            script.forEach_invert(inputAllocation, outputAllocation)
            outputAllocation.copyTo(targetBitmap)

            rs.destroy()
            inputAllocation.destroy()
            outputAllocation.destroy()

            return targetBitmap
        }

        fun greyscale(context: Context, bitmap: Bitmap): Bitmap {
            val targetBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)

            val rs = RenderScript.create(context)
            val script = ScriptC_singlesource(rs)
            val inputAllocation = Allocation.createFromBitmap(rs, bitmap)
            val outputAllocation = Allocation.createFromBitmap(rs, targetBitmap)

            script.forEach_greyscale(inputAllocation, outputAllocation)
            outputAllocation.copyTo(targetBitmap)

            rs.destroy()
            inputAllocation.destroy()
            outputAllocation.destroy()

            return targetBitmap
        }

        fun greyscaleByWeighting(context: Context, bitmap: Bitmap): Bitmap {
            val targetBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)

            val rs = RenderScript.create(context)
            val script = ScriptC_singlesource(rs)
            val inputAllocation = Allocation.createFromBitmap(rs, bitmap)
            val outputAllocation = Allocation.createFromBitmap(rs, targetBitmap)

            script.forEach_greyscaleByWeighting(inputAllocation, outputAllocation)
            outputAllocation.copyTo(targetBitmap)

            rs.destroy()
            inputAllocation.destroy()
            outputAllocation.destroy()

            return targetBitmap
        }

        fun process(context: Context, bitmap: Bitmap): Bitmap {
            val targetBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)

            val rs = RenderScript.create(context)
            val script = ScriptC_singlesource(rs)
            val inputAllocation = Allocation.createFromBitmap(rs, bitmap)
            val outputAllocation = Allocation.createFromBitmap(rs, targetBitmap)

            script.invoke_process(inputAllocation, outputAllocation)
            outputAllocation.copyTo(targetBitmap)

            rs.destroy()
            inputAllocation.destroy()
            outputAllocation.destroy()

            return targetBitmap
        }
//
//        fun process(context: Context, bitmap: Bitmap): Bitmap {
//
//        }
    }
}