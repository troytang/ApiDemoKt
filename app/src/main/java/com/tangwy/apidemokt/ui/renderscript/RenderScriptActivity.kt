package com.tangwy.apidemokt.ui.renderscript

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.tangwy.apidemokt.R
import com.tangwy.apidemokt.framework.renderscript.RenderScriptAPI
import kotlinx.android.synthetic.main.content_render_script.*

class RenderScriptActivity : AppCompatActivity(), View.OnClickListener {

    private var mOriginImage: Bitmap? = null

    override fun onClick(v: View?) {
        val startTime = System.currentTimeMillis()
        when (v) {
            blur -> iv_target.setImageBitmap(RenderScriptAPI.blur(this, mOriginImage!!))
            invert -> iv_target.setImageBitmap(RenderScriptAPI.invert(this, mOriginImage!!))
            greyscale -> iv_target.setImageBitmap(RenderScriptAPI.greyscale(this, mOriginImage!!))
            greyscale_by_weighting -> iv_target.setImageBitmap(RenderScriptAPI.greyscaleByWeighting(this, mOriginImage!!))
            process -> iv_target.setImageBitmap(RenderScriptAPI.process(this, mOriginImage!!))
        }
        Toast.makeText(this, "cost ${System.currentTimeMillis() - startTime} ms.",
                Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_render_script)

        val vectorDrawable = resources.getDrawable(R.mipmap.ic_launcher_round);
        mOriginImage = Bitmap.createBitmap(vectorDrawable.intrinsicWidth,
                vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(mOriginImage)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)

        iv_origin.setImageBitmap(mOriginImage)
        iv_target.setImageBitmap(mOriginImage)

        blur.setOnClickListener(this)
        invert.setOnClickListener(this)
        greyscale.setOnClickListener(this)
        greyscale_by_weighting.setOnClickListener(this)
        process.setOnClickListener(this)
    }
}