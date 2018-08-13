package com.tangwy.apidemokt.framework.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import android.widget.RemoteViews
import com.tangwy.test.R
import com.tangwy.apidemokt.ui.notification.NotificationActivity
import com.tangwy.apidemokt.ui.notification.NotificationSpecialActivity
import com.tangwy.apidemokt.util.Device
import java.util.*

class NotificationTest {

    companion object {

        @SuppressLint("NewApi")
        fun notify(context: Context, title: String, content: String) {

            val id = Random().nextInt(1000)
            val channelId = "channelId$id"
            val channelName = "channelName"
            var builder: NotificationCompat.Builder = NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setAutoCancel(true)

            // 创建一个目标页面的 Intent
            var resultIntent = Intent(context, NotificationActivity::class.java)

            var stackBuilder: TaskStackBuilder = TaskStackBuilder.create(context)
            stackBuilder.addParentStack(NotificationActivity::class.java)
            stackBuilder.addNextIntent(resultIntent)

            // 包裹 Intent
            var pendingIntent: PendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)!!
            builder.setContentIntent(pendingIntent)

            var notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Device.afterO()) {
                val nc = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(nc)
            }
            notificationManager.notify(id, builder.build())
        }

        @SuppressLint("NewApi")
        fun notifyWithStyle(context: Context, title: String, content: String) {

            val id = Random().nextInt(1000)
            val channelId = "channel$id"
            val channelName = "channelName"
            var builder: NotificationCompat.Builder = NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setAutoCancel(true)

            // 扩展布局
            var inboxStyle = NotificationCompat.InboxStyle()
            var events = arrayListOf<String>()
            events.add("First")
            events.add("Second")
            events.add("Third")
            inboxStyle.setBigContentTitle("Big content title")
            for (event in events) {
                inboxStyle.addLine(event)
            }
            builder.setStyle(inboxStyle)

            // 创建跳转页面的 Intent
            val resultIntent = Intent(context, NotificationActivity::class.java)
            val stackBuilder = TaskStackBuilder.create(context)
            stackBuilder.addParentStack(NotificationActivity::class.java)
            stackBuilder.addNextIntent(resultIntent)

            // 包裹 Intent
            val pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            builder.setContentIntent(pendingIntent)

            var notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Device.afterO()) {
                val nc = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(nc)
            }
            notificationManager.notify(id, builder.build())
        }

        @SuppressLint("NewApi")
        fun notifyUpdate(context: Context, title: String, content: String, id: Int) {

            val channelId = "channel$id"
            val channelName = "channelName"
            val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, channelId)
                    .setAutoCancel(false)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle(title)
                    .setContentText(content)

            // 创建一个跳转页面的 Intent
            val intent = Intent(context, NotificationActivity::class.java)
            val stackBuilder = TaskStackBuilder.create(context)
            stackBuilder.addParentStack(NotificationActivity::class.java)
            stackBuilder.addNextIntent(intent)

            // 包裹 Intent
            val pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            builder.setContentIntent(pendingIntent)

            val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Device.afterO()) {
                val nc = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(nc)
            }
            notificationManager.notify(id, builder.build())
        }

        @SuppressLint("NewApi")
        fun notifySpecial(context: Context, title: String, content: String) {

            val id = Random().nextInt(1000)
            val chanelId = "channel$id"
            val channelName = "channelName"
            val builder = NotificationCompat.Builder(context, chanelId)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setAutoCancel(false)
                    .setContentTitle(title)
                    .setContentText(content)

            val intent = Intent(context, NotificationSpecialActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            builder.setContentIntent(pendingIntent)
            val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Device.afterO()) {
                val nc = NotificationChannel(chanelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(nc)
            }
            notificationManager.notify(id, builder.build())
        }

        @SuppressLint("NewApi")
        fun notifyProgress(context: Context, title: String, content: String) {

            val id = Random().nextInt(1000)
            val channelId = "channel$id"
            val channelName = "channelName"
            val builder = NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setAutoCancel(false)

            val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Device.afterO()) {
                val nc = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(nc)
            }

            // 起线程模拟异步
            Thread(Runnable {
                // 开始进度
                for (i in 0..100) {
                    builder.setProgress(100, i, false)
                    notificationManager.notify(id, builder.build())
                    Thread.sleep(1000)
                }

                // 结束，隐藏进度条
                builder.setProgress(0, 0, false)
                builder.setContentText("Completed!")
                notificationManager.notify(id, builder.build())
            }).start()
        }

        @SuppressLint("NewApi")
        fun notifyActivityIndicator(context: Context, title: String, content: String) {

            val id = Random().nextInt(1000)
            val channelId = "channel$id"
            val channelName = "channelName"
            val buidler = NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setAutoCancel(false)

            val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Device.afterO()) {
                val nc = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(nc)
            }
            notificationManager.notify(id, buidler.build())

            // 创建一个线程模拟
            Thread(Runnable {
                for (i in 0..100) {
                    buidler.setProgress(0, 0, true)
                    notificationManager.notify(id, buidler.build())
                    Thread.sleep(1000)
                }

                // 结束
                buidler.setProgress(0, 0, false)
                buidler.setContentText("Completed!")
                notificationManager.notify(id, buidler.build())
            }).start()
        }

        @SuppressLint("NewApi")
        fun notifyCustom(context: Context, packageName: String, resId: Int) {

            val customView = RemoteViews(packageName, resId)

            val id = Random().nextInt(1000)
            val channelId = "channel$id"
            val channelName = "channelName"
            val builder = NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContent(customView)
                    .setAutoCancel(false)

            val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Device.afterO()) {
                val nc = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(nc)
            }
            notificationManager.notify(id, builder.build())
        }
    }
}