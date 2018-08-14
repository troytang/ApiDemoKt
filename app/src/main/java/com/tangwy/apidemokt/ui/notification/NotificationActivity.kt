package com.tangwy.apidemokt.ui.notification

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.tangwy.apidemokt.R
import com.tangwy.apidemokt.framework.notification.NotificationService
import com.tangwy.apidemokt.framework.notification.NotificationAPI
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.content_notification.*
import java.util.*

class NotificationActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v) {
            common -> NotificationAPI.notify(this, "Common", "Content")
            inbox_style -> NotificationAPI.notifyWithStyle(this, "InboxStyle", "Content")
            update -> {
                val id = Random().nextInt(1000)
                var num = 1
                NotificationAPI.notifyUpdate(this, "Original", num.toString(), id)
                Handler().postDelayed({
                    num++
                    NotificationAPI.notifyUpdate(this, "Update", num.toString(), id)
                }, 5000)
            }
            special -> NotificationAPI.notifySpecial(this, "Special", "Content")
            progress -> NotificationAPI.notifyProgress(this, "Progress", "Content")
            activity_indicator -> NotificationAPI.notifyActivityIndicator(this, "Progress", "Content")
            custom_view -> NotificationAPI.notifyCustom(this, packageName, R.layout.notification_custom)
            fab -> {
                val serviceIntent = Intent(applicationContext, NotificationService::class.java)
                startService(serviceIntent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        fab.setOnClickListener(this)
        common.setOnClickListener(this)
        inbox_style.setOnClickListener(this)
        update.setOnClickListener(this)
        special.setOnClickListener(this)
        progress.setOnClickListener(this)
        activity_indicator.setOnClickListener(this)
        custom_view.setOnClickListener(this)
    }
}