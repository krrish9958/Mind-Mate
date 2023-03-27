package com.example.mindmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlin.math.sign

class LoggedOutActivity : AppCompatActivity() {
    private lateinit var signInBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_out)
        signInBtn = findViewById(R.id.signInBtn)
        signInBtn.setOnClickListener {
            startActivity(Intent(this@LoggedOutActivity,MainActivity::class.java))
        }
    }
}