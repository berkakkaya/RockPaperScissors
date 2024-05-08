package com.berkakkaya.rockpaperscissors

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class Choice {
    ROCK, PAPER, SCISSORS
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun pickChoicePressed(view: View) {
        lifecycleScope.launch { pickChoice() }
    }

    private suspend fun pickChoice() {
        return withContext(Dispatchers.IO) {
            // Pick a random chocice
            val choice: Choice = Choice.entries.random()

            // Wait for 5 secs
            delay(5000L)

            // Display the result in UI
            runOnUiThread {
                val imageView: ImageView = findViewById(R.id.imageView)

                imageView.setImageResource(when(choice) {
                    Choice.ROCK -> R.drawable.rock
                    Choice.PAPER -> R.drawable.paper
                    else -> R.drawable.scissors
                })
            }
        }
    }
}
