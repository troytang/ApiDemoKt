package com.tangwy.apidemokt.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.tangwy.apidemokt.ui.dialog.DialogActivity
import com.tangwy.apidemokt.R
import com.tangwy.apidemokt.ui.notification.NotificationActivity
import com.tangwy.apidemokt.ui.renderscript.RenderScriptActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v) {
            fab -> Snackbar.make(v!!, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            notification -> {
                val intent = Intent(this, NotificationActivity::class.java)
                startActivity(intent)
            }
            dialog -> {
                val intent = Intent(this, DialogActivity::class.java)
                startActivity(intent)
            }
            render_script -> {
                val intent = Intent(this, RenderScriptActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener(this)
        notification.setOnClickListener(this)
        dialog.setOnClickListener(this)
        render_script.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
