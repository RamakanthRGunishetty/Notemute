package com.sunayanpradhan.digitalinkrecognition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        var btnregister = findViewById<Button>(R.id.btnregister)
        var emaill = findViewById<TextInputEditText>(R.id.email)
        var passwordd = findViewById<TextInputEditText>(R.id.password)
        var progressBar = findViewById<ProgressBar>(R.id.progress)
        var text = findViewById<TextView>(R.id.loginnow)

        text.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

        btnregister.setOnClickListener {
            var email = emaill.text.toString()
            var password = passwordd.text.toString()
            progressBar.visibility = View.VISIBLE

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        progressBar.visibility = View.INVISIBLE
                        Toast.makeText(
                            this,
                            "Account created successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        progressBar.visibility = View.INVISIBLE
                        Toast.makeText(
                            this,
                            "Registration failed. ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
