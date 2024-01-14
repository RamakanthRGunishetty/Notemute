package com.sunayanpradhan.digitalinkrecognition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class account : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        auth = FirebaseAuth.getInstance()
        var btn = findViewById<Button>(R.id.logout)
        var text = findViewById<TextView>(R.id.userdetails)
        val user = auth.currentUser

        if (user == null) {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        } else {
            text.text = user.email
        }

        btn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
            finish()
        }
    }
}
