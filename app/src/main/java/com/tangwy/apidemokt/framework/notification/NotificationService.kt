package com.tangwy.apidemokt.framework.notification

import android.app.IntentService
import android.content.Intent

class NotificationService(name: String): IntentService(name) {

    constructor() : this("default")

    override fun onHandleIntent(intent: Intent?) {
        notifyMsg()
    }

    private fun notifyMsg() {
        NotificationTest.notify(applicationContext, "First Title", "First Content")

        Thread.sleep(2000)
        NotificationTest.notify(applicationContext, "中文名字试下？", "中文内容没有感叹号")

        Thread.sleep(2000)
        NotificationTest.notify(applicationContext, "没有感叹号", "中文内容试下\\！")

        Thread.sleep(2000)
        NotificationTest.notify(applicationContext, "Second Title", "Second Content")
    }
}