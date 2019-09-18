package com.example.realcapston

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_setting.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Suppress("DEPRECATION")
class SettingFragment : Fragment() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelId = "com.example.realcapston"
    private val description = "테스트 노티피케이션"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        this.notificationManager = activity!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        this.button_notify.setOnClickListener {

            val intent=Intent(activity,MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(activity,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

            val contentView = RemoteViews(activity!!.packageName,R.layout.notification_layout)
            contentView.setTextViewText(R.id.tv_name,"RealCapston"+ CustomTime())
            contentView.setTextViewText(R.id.tv_title,"경고!")
            contentView.setTextViewText(R.id.tv_content,"모니터링중 문제가 발생하였습니다.")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_DEFAULT)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor= Color.GREEN
                notificationChannel.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChannel)

                builder= Notification.Builder(activity,channelId)
                    .setContent(contentView)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            }
            else
            {
                builder = Notification.Builder(activity)
                    .setContent(contentView)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            }
            notificationManager.notify(0,builder.build())
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun CustomTime(): String? {
        val current = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("  a h:mm")
        val formatted = current.format(formatter)
        return formatted

    }
}