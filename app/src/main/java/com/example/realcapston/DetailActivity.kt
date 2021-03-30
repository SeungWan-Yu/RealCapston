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
import android.view.Menu
import android.widget.RemoteViews
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.github.mikephil.charting.charts.LineChart
import kotlinx.android.synthetic.main.activity_detail.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter


class DetailActivity : AppCompatActivity() {

    lateinit var notificationChannel: NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelId = "com.example.realcapston"
    private val description = "테스트 노티피케이션"
    private val LineChart = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (intent.hasExtra("comId")) {
            val comId= intent.getStringExtra("comId")
            Toast.makeText(this, "전달된 값은 $comId 입니", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "전달된 이름이 없습니다", Toast.LENGTH_SHORT).show()
        }

        button_noty.setOnClickListener {
            noty()
        }
        this.supportActionBar?.title = getString(R.string.detail)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detailmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("WrongConstant")
    private fun noty() {

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent= Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val contentView = RemoteViews(this!!.packageName, R.layout.notification_layout)
        contentView.setTextViewText(R.id.tv_name, "RealCapston" + CustomTime())
        contentView.setTextViewText(R.id.tv_title, "경고!")
        contentView.setTextViewText(R.id.tv_content, "모니터링중 문제가 발생하였습니다.")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(
                channelId,
                description,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor= Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder= Notification.Builder(this, channelId)
                .setContent(contentView)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
        }
        else
        {
            builder = Notification.Builder(this)
                .setContent(contentView)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
        }
        notificationManager.notify(0, builder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun CustomTime(): Any? {
        val current = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("  a h:mm")
        val formatted = current.format(formatter)
        return formatted
    }
}
