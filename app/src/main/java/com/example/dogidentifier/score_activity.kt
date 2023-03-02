package com.example.dogidentifier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class score_activity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_score)

            val correctGuesses = intent.getIntExtra("correctGuesses", 0)
            val incorrectGuesses = intent.getIntExtra("incorrectGuesses", 0)

            val correctTextView = findViewById<TextView>(R.id.correctTextView)
            correctTextView.text = "Correct guesses: $correctGuesses"

            val incorrectTextView = findViewById<TextView>(R.id.incorrectTextView)
            incorrectTextView.text = "Incorrect guesses: $incorrectGuesses"
        }

}