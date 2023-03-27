package com.example.mindmate



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoggedInActivity : AppCompatActivity() {
    private lateinit var continueBtn : Button
    private lateinit var logoutBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)
        continueBtn = findViewById(R.id.continueBtn)
        continueBtn.setOnClickListener {
            startActivity(Intent(this@LoggedInActivity,FeelingsActivity::class.java))
        }

        logoutBtn = findViewById(R.id.logoutBtn)
        logoutBtn.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(this@LoggedInActivity, LoggedOutActivity::class.java))
        }
    }
}