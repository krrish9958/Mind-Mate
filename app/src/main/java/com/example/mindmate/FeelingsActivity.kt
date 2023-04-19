package com.example.mindmate

import android.content.Intent
import android.os.Build.VERSION_CODES.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.mindmate.utilities.*
import com.google.firebase.auth.FirebaseAuth

class FeelingsActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var username : TextView
    private lateinit var etMood : EditText
    private lateinit var tvEmoji : TextView
    private lateinit var saveBtn : Button
    private lateinit var continueButton : Button
    private lateinit var happyKeywords : ArrayList<String>
    private lateinit var sadKeywords : ArrayList<String>
    private lateinit var angryKeywords : ArrayList<String>
    private lateinit var depressedKeywords : ArrayList<String>
    private lateinit var anxiousKeywords : ArrayList<String>
    private lateinit var scaredKeywords : ArrayList<String>
    private lateinit var tiredKeywords : ArrayList<String>
    private lateinit var cryingKeywords : ArrayList<String>
    private lateinit var mehKeywords : ArrayList<String>
    private lateinit var relievedKeywords : ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feelings)
        username = findViewById(R.id.usernameFeelings)
        etMood = findViewById(R.id.etMood)
        continueButton = findViewById(R.id.continueBtn)
        tvEmoji = findViewById(R.id.tvEmoji)
        continueButton.setOnClickListener {
            startActivity(Intent(this@FeelingsActivity, HomeActivity::class.java))
        }

        keywordsDefinition()
        detectMood()

    }

    private fun detectMood() {
        val userMood = etMood.text.toString().toLowerCase().trim()

        var detectedMood : String? = null

        if (happyKeywords.any{ userMood.contains(it) }) {
            detectedMood = "happy"
        }
        else if (sadKeywords.any{ userMood.contains(it)}){
            detectedMood = "sad"
        }
        else if (angryKeywords.any{ userMood.contains(it)}){
            detectedMood = "angry"
        }
        else if (depressedKeywords.any { userMood.contains(it) }){
            detectedMood = "depressed"
        }
        else if(anxiousKeywords.any { userMood.contains(it) }){
            detectedMood = "anxious"
        }
        else if (scaredKeywords.any{ userMood.contains(it)}){
            detectedMood = "scared"
        }
        else if (tiredKeywords.any{ userMood.contains(it)}){
            detectedMood = "tired"
        }
        else if (cryingKeywords.any{ userMood.contains(it)}){
            detectedMood = "crying"
        }
        else if (mehKeywords.any{ userMood.contains(it)}){
            detectedMood = "meh"
        }
        else if (relievedKeywords.any{ userMood.contains(it)}){
            detectedMood = "relieved"
        }

        when (detectedMood){
            "happy" -> {
                tvEmoji.text = HAPPY_EMOJI
            }
            "sad" -> {
                tvEmoji.text = SAD_EMOJI
            }
            "depressed" -> {
                tvEmoji.text = DEPRESSED_EMOJI
            }
            "angry" -> {
                tvEmoji.text = ANGRY_EMOJI
            }
            "anxious" -> {
                tvEmoji.text = NEUTRAL_EMOJI
            }
            "crying" -> {
                tvEmoji.text = CRYING_EMOJI
            }
            "scared" -> {
                tvEmoji.text = SCARED_EMOJI
            }
            "tired" -> {
                tvEmoji.text = TIRED_EMOJI
            }
            "meh" -> {
                tvEmoji.text = IDK_EMOJI
            }
            else -> {
                tvEmoji.text = IDK_EMOJI
            }
        }
    }

    private fun keywordsDefinition() {
        happyKeywords = arrayListOf(
            "happy", "smiling", "pleased", "cheerful" , "merry", "delighted", "peaceful", "elated",
            "lively", "pleasant", "ecstatic", "content", "grateful", "radiant", "amused"
        )

        sadKeywords = arrayListOf(
            "sad", "sorry", "unhappy", "mournful", "heartbroken", "cheerless", "grief", "hurting", "hurt",
            "gloomy", "dull", "low", "troubled", "sick at heart", "not happy", "down", "overwhelmed", "upset",
            "blue", "miserable", "distressed", "heavy hearted", "tearful", "frustrated"
        )

        angryKeywords = arrayListOf(
            "annoyed", "angry", "offended", "bitter", "enraged", "furious", "heated", "irritated",
            "resentful", "mad", "uptight", "irritable", "irritating", "pissed", "provoked", "sulking",
            "raging", "fuming", " hateful"
        )

        depressedKeywords = arrayListOf(
            "depressed", "hopeless", "numb", "isolated", "alone", "joyless", "defeated", "discouraged",
            "disheartened", "lifeless", "empty", "null", "nothing", "despairing"
        )

        anxiousKeywords = arrayListOf(
            "anxious", "nervous", "anxiety", "worried", "uneasy", "tense", "restless", "stressed", "panicky",
            "panick", "unsettled", "insecure"
        )

        scaredKeywords = arrayListOf(
            "fearful", "scared", "terrifed", "horror", "frightened", "startled", "shaken", "horror stricken"
        )

        tiredKeywords = arrayListOf(
            "tired", "sleepy", "fatigued", "exhausted", "drained", "weary", "lethargic", "worn out",
            "ready to drop", "burnt out", "heavy eyed"
        )

        cryingKeywords = arrayListOf(
            "crying", "weeping", "sobbing", "wailing", "bawling", "emotional", "pained", "agony", "misery",
            "woe"
        )
        mehKeywords = arrayListOf(
            "meh", "blah", "don't know", "bored", "unimpressed"
        )

        relievedKeywords = arrayListOf(
            "relieved", "calm", "thankful", "released", "eased", "soothed", "glad"
        )
    }
}