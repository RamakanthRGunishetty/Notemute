package com.sunayanpradhan.digitalinkrecognition

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.sunayanpradhan.digitalinkrecognition.StrokeManager.clear
import com.sunayanpradhan.digitalinkrecognition.StrokeManager.download
import com.sunayanpradhan.digitalinkrecognition.StrokeManager.recognize

class MainActivity : AppCompatActivity() {
    private lateinit var btnRecognize: Button
    private lateinit var btnClear: Button
    private lateinit var drawView: DrawView
    private lateinit var textView: TextView
    private lateinit var searchbtn:Button
    private lateinit var account:Button

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        btnRecognize = findViewById(R.id.buttonRecognize)
        btnClear = findViewById(R.id.buttonClear)
        drawView = findViewById(R.id.draw_view)
        textView = findViewById(R.id.textResult)
        searchbtn=findViewById(R.id.search)
        account=findViewById(R.id.account)



        account.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
            finish()
        }

        hideTitleBar()

        download(applicationContext)

        btnRecognize.setOnClickListener {
            StrokeManager.recognize(textView)
        }
        searchbtn.setOnClickListener{
            StrokeManager.recognizeAndSearch(textView,this)
        }
        btnClear.setOnClickListener {
            drawView.clear()
            clear()
            textView.text = ""
        }
    }

    private fun hideTitleBar() {
        this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }

    override fun onResume() {
        super.onResume()
        hideTitleBar()
    }

    override fun onPause() {
        super.onPause()
        hideTitleBar()
    }

    override fun onStop() {
        super.onStop()
        hideTitleBar()
    }
}
