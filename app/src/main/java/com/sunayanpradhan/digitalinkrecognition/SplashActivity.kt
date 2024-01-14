// SplashActivity.kt
package com.sunayanpradhan.digitalinkrecognition

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 2000 // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }, SPLASH_TIME_OUT)
    }
}
