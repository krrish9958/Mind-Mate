package com.example.mindmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mindmate.databinding.ActivityLoggedInBinding


class LoggedInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoggedInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoggedInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // vals if the user logs in using email
        val username = intent.getStringExtra("username").toString()
        val email = intent.getStringExtra("email").toString()

        // vals if the user logs in using google
        val gName = intent.getStringExtra("gName").toString()
        val gmail = intent.getStringExtra("gmail").toString()


        binding.navigateToFeelingsBtn.setOnClickListener {
            val intent = Intent(this@LoggedInActivity, FeelingsActivity::class.java)
            intent.putExtra("name", username)
            intent.putExtra("email", email)
            intent.putExtra("gName", gName)
            intent.putExtra("gmail", gmail)
            startActivity(intent)
        }

        binding.navigateToHomeBtn.setOnClickListener {
            val intent = Intent(this@LoggedInActivity, HomeActivity::class.java)
            startActivity(intent)
        }

    }
}