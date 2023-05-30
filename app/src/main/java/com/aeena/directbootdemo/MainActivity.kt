package com.aeena.directbootdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        Intent.ACTION_LOCKED_BOOT_COMPLETED // BoardCast send before user unlock the device

        Intent.ACTION_BOOT_COMPLETED // BoardCast send when user unlock the device
        */

        val dpContext = createDeviceProtectedStorageContext()
        val sharedPreferences = dpContext.getSharedPreferences(
            "DEVICE_PROTECTED_PREFERENCES",
            MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.putString("notify_text", "I was saved in Device Protected Storage!")
        editor.apply()
    }
}