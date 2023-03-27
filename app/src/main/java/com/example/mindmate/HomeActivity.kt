package com.example.mindmate

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        replaceFragment(HomeFragment())
        val acct = GoogleSignIn.getLastSignedInAccount(this@HomeActivity)
        val personName = acct?.displayName
        val personEmail = acct?.email
        val personId = acct?.id
        val personPhoto = acct?.photoUrl

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        frameLayout = findViewById(R.id.framelayout)
        bottomNavigationView.setOnItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.journal -> {
                    replaceFragment(JournalFragment())
                    true
                }
                R.id.recommended -> {
                    replaceFragment(RecommendedFragment())
                    true
                }
                R.id.explore -> {
                    replaceFragment(ExploreFragment())
                    true
                }
                else -> {
                    replaceFragment(ProfileFragment())
                    true
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, fragment)
        fragmentTransaction.commit()
    }
}