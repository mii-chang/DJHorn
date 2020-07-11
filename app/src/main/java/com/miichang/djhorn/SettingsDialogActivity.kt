package com.miichang.djhorn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SettingsDialogActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context): Intent = Intent(
            context,
            SettingsDialogActivity::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(
                    R.id.container,
                    SettingsDialogFragment.newInstance()
                )
            }.commitNow()
        }
    }


}