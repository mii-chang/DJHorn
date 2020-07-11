package com.miichang.djhorn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object {
        const val SOUND_ID = "soundId"
        const val REQUEST = 1000
        const val RESULT = 200
    }

    var soundId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(
                    R.id.container,
                    MainFragment.newInstance()
                )
            }.commitNow()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST) {
            if (resultCode == RESULT) {
                soundId = data?.extras?.getInt(SOUND_ID, 0) ?: 0
                Log.e("sound main", soundId.toString())
            }
        }

    }
}