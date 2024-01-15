package com.sunayanpradhan.digitalinkrecognition

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class account : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)


        val btnLogout = findViewById<Button>(R.id.logout)
        val textUserDetails = findViewById<TextView>(R.id.userdetails)
        val user = auth.currentUser

        if (user == null) {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        } else {
            textUserDetails.text = user.email
        }

        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
            finish()
        }
    }
}
