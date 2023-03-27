package com.example.mindmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class FeelingsActivity : AppCompatActivity() {
    private lateinit var continueButton : Button
    private lateinit var auth: FirebaseAuth
    private lateinit var username : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feelings)
        auth = FirebaseAuth.getInstance()
        val name = intent.getStringExtra("name")
        username = findViewById(R.id.username)
        username.text = "Hey " + name
        continueButton = findViewById(R.id.continueButton)
        continueButton.setOnClickListener {
            startActivity(Intent(this@FeelingsActivity, HomeActivity::class.java))
        }
    }
}