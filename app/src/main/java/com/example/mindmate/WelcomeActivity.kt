package com.example.mindmate

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.ImageViewTarget
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log

class WelcomeActivity : AppCompatActivity() {

    private lateinit var welcomeGif : ImageView
    private lateinit var loginBtn : Button
    private lateinit var signUpBtn : Button
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        welcomeGif = findViewById(R.id.welcomeGif)
        Glide.with(this)
            .asGif()
            .load(R.drawable.welcome)
            .into(object : ImageViewTarget<GifDrawable>(welcomeGif) {
                override fun setResource(resource: GifDrawable?) {
                    resource?.setColorFilter(Color.rgb(136, 121, 212), PorterDuff.Mode.MULTIPLY)
                    welcomeGif.setImageDrawable(resource)
                }
            })

        loginBtn = findViewById(R.id.welcomeLogin)
        signUpBtn = findViewById(R.id.welcomeSignup)
        loginBtn.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity,LoginActivity::class.java))
        }
        signUpBtn.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, SignUpActivity::class.java))
        }

        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail().build()
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser!=null){
            startActivity(Intent(this@WelcomeActivity, FeelingsActivity::class.java))
            finish()
        }
        else{
            Toast.makeText(this@WelcomeActivity, "You need to login", Toast.LENGTH_SHORT).show()
        }
    }

}