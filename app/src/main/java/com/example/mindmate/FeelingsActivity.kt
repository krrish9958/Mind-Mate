package com.example.mindmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.mindmate.utilities.*

class FeelingsActivity : AppCompatActivity() {
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
        saveBtn = findViewById(R.id.saveMood)

        etMood.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                keywordsDefinition()
                detectMood()
            }
        })


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
            "disheartened", "lifeless", "empty", "null", "nothing", "despairing", "depress", "depression", "depressing"
        )

        anxiousKeywords = arrayListOf(
            "anxious", "nervous", "anxiety", "worried", "uneasy", "tense", "restless", "stressed", "panicky",
            "panic", "unsettled", "insecure"
        )

        scaredKeywords = arrayListOf(
            "fearful", "scared", "terrified", "horror", "frightened", "startled", "shaken", "horror stricken"
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

    private fun detectMood() {
        val userMood = etMood.text.toString().toLowerCase().trim()

        var detectedMood : String? = null

        if (happyKeywords.contains(userMood)) {
            detectedMood = "happy"
            tvEmoji.text = HAPPY_EMOJI
        }
        else if (sadKeywords.contains(userMood)){
            detectedMood = "sad"
            tvEmoji.text = SAD_EMOJI
        }
        else if (angryKeywords.contains(userMood)){
            detectedMood = "angry"
            tvEmoji.text = ANGRY_EMOJI
        }
        else if (depressedKeywords.contains(userMood)){
            detectedMood = "depressed"
            tvEmoji.text = DEPRESSED_EMOJI
        }
        else if(anxiousKeywords.contains(userMood)){
            detectedMood = "anxious"
            tvEmoji.text = NEUTRAL_EMOJI
        }
        else if (scaredKeywords.contains(userMood)){
            detectedMood = "scared"
            tvEmoji.text = SCARED_EMOJI
        }
        else if (tiredKeywords.contains(userMood)){
            detectedMood = "tired"
            tvEmoji.text = TIRED_EMOJI
        }
        else if (cryingKeywords.contains(userMood)){
            detectedMood = "crying"
            tvEmoji.text = CRYING_EMOJI
        }
        else if (mehKeywords.contains(userMood)){
            detectedMood = "meh"
            tvEmoji.text = IDK_EMOJI
        }
        else if (relievedKeywords.contains(userMood)){
            detectedMood = "relieved"
            tvEmoji.text = RELEIVED_EMOJI
        }
        else{
            tvEmoji.text = ""
        }
    }
}