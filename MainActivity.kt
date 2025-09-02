package com.example.meals

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputTime: EditText
    private lateinit var txtSuggestion: TextView
    private lateinit var btnSuggest: Button
    private lateinit var btnReset: Button

    // For rotation state
    private var currentSuggestion: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        inputTime = findViewById(R.id.inputTimeOfDay)
        txtSuggestion = findViewById(R.id.txtSuggestion)
        btnSuggest = findViewById(R.id.btnSuggest)
        btnReset = findViewById(R.id.btnReset)

        savedInstanceState?.let {
            currentSuggestion = it.getString("suggestion", "")
            txtSuggestion.text = if (currentSuggestion.isBlank())
                "Your suggestion will appear here" else currentSuggestion
        }

        btnSuggest.setOnClickListener {
            val raw = inputTime.text.toString().trim()
            Log.d("MealHelper", "User input: $raw")

            if (raw.isBlank()) {
                showError("Please type a time of day, e.g. Morning or Dinner.")
                return@setOnClickListener
            }

            // Normalize input to make checks more forgiving
            val t = raw.lowercase()

            val suggestion = when {
                t == "morning" -> "Breakfast: Pancakes or a Smoothie 🥞🥤"
                t == "mid-morning" || t == "mid morning" -> "Light snack: Fruit & yoghurt 🍎🥣"
                t == "afternoon" -> "Lunch: Sandwich or Salad 🥪🥗"
                t == "afternoon snack" || t == "snack" -> "Quick bites: Cookies & milk 🍪🥛"
                t == "dinner" -> "Main: Pasta or Stir Fry 🍝🍜"
                t == "after dinner" || t == "dessert" -> "Dessert: Ice cream or Fruit salad 🍨🍉"
                else -> null
            }

            if (suggestion == null) {
                showError("Sorry, I don't recognise \"$raw\". Try: Morning, Mid-morning, Afternoon, Afternoon snack, Dinner, After dinner.")
            } else {
                currentSuggestion = suggestion
                txtSuggestion.text = suggestion
                Log.i("MealHelper", "Suggestion displayed: $suggestion")
            }
        }

        btnReset.setOnClickListener {
            inputTime.setText("")
            currentSuggestion = ""
            txtSuggestion.text = "Your suggestion will appear here"
            Toast.makeText(this, "Cleared.", Toast.LENGTH_SHORT).show()
            Log.d("MealHelper", "Inputs cleared")
        }
    }

    private fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        Log.w("MealHelper", "Validation error: $msg")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("suggestion", currentSuggestion)
    }
}
