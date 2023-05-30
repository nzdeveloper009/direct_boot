package com.aeena.directbootdemo

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.createDeviceProtectedStorageContext

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_LOCKED_BOOT_COMPLETED == intent.action) {
            val dpContext = context.createDeviceProtectedStorageContext()
            val sharedPreferences = dpContext.getSharedPreferences(
                "DEVICE_PROTECTED_PREFERENCES",
                AppCompatActivity.MODE_PRIVATE
            )
            val notifyText = sharedPreferences.getString("notify_text", null)
            if (notifyText != null) {
                val notificationManagerCompat = NotificationManagerCompat.from(context)
                val notification = NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(
                        BitmapFactory.decodeResource(
                            context.resources,
                            R.mipmap.ic_launcher
                        )
                    )
                    .setContentText(notifyText)
                    .build()

                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                notificationManagerCompat.notify(1, notification)
            }
        }
    }
}