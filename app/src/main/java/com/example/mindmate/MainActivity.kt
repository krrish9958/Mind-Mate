package com.example.mindmate



import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {
    // vars for google authentication
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInclient: GoogleSignInClient

    //instantiating views
    private  lateinit var signUptextview : TextView
    private lateinit var loginButton: Button
    private lateinit var forgotPasswordTv : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail().build()
        googleSignInclient = GoogleSignIn.getClient(this, gso)
        findViewById<Button>(R.id.gBtnLogin).setOnClickListener {
            signInGoogle()
        }

        //intents
        signUptextview = findViewById(R.id.signUpLogin)
        signUptextview.setOnClickListener{
            startActivity(Intent(this@MainActivity,SignUpActivity::class.java))
        }

        forgotPasswordTv = findViewById(R.id.loginForgotPassword)
        forgotPasswordTv.setOnClickListener {
            startActivity(Intent(this@MainActivity, ForgotPasswordActivity::class.java))
        }

        loginButton = findViewById(R.id.loginBtn)
        loginButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, LoggedInActivity::class.java))
        }
    }

    private fun signInGoogle() {
        val signInIntent = googleSignInclient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleResults(task)
            }
        }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result
            if (account != null) {
                updateUI(account)
            }
        } else {
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val intent: Intent = Intent(this, LoggedInActivity::class.java)
                intent.putExtra("email", account.email)
                intent.putExtra("name", account.displayName)
                startActivity(intent)
            } else {
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}