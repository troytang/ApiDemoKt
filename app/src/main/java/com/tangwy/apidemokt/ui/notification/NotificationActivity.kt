package com.tangwy.apidemokt.ui.notification

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.tangwy.apidemokt.R
import com.tangwy.apidemokt.framework.notification.NotificationService
import com.tangwy.apidemokt.framework.notification.NotificationAPI
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.content_notification.*
import java.util.*

class NotificationActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        fab.setOnClickListener {
            startNotificationService()
        }

        common.setOnClickListener {
            notifyCommon()
        }

        inbox_style.setOnClickListener {
            notifyInboxStyle()
        }

        update.setOnClickListener {
            notifyUpdate()
        }

        special.setOnClickListener {
            notifySpecial()
        }

        progress.setOnClickListener {
            notifyProgress()
        }

        activity_indicator.setOnClickListener {
            notifyActivityIndicator()
        }

        custom_view.setOnClickListener {
            notifyCustom()
        }
    }

    private fun startNotificationService() {
        val serviceIntent = Intent(applicationContext, NotificationService::class.java)
        startService(serviceIntent)
    }

    private fun notifyCommon() {
        NotificationAPI.notify(this, "Common", "Content")
    }

    private fun notifyInboxStyle() {
        NotificationAPI.notifyWithStyle(this, "InboxStyle", "Content")
    }

    private fun notifySpecial() {
        NotificationAPI.notifySpecial(this, "Special", "Content")
    }

    private fun notifyProgress() {
        NotificationAPI.notifyProgress(this, "Progress", "Content")
    }

    private fun notifyActivityIndicator() {
        NotificationAPI.notifyActivityIndicator(this, "Progress", "Content")
    }

    private fun notifyCustom() {
        NotificationAPI.notifyCustom(this, packageName, R.layout.notification_custom)
    }

    private fun notifyUpdate() {
        val id = Random().nextInt(1000)
        var num = 1
        NotificationAPI.notifyUpdate(this, "Original", num.toString(), id)
        Handler().postDelayed({
            num++
            NotificationAPI.notifyUpdate(this, "Update", num.toString(), id)
        }, 5000)
    }
}