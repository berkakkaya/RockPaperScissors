package com.berkakkaya.rockpaperscissors

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

enum class MoveChoice {
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

    fun convertMoveToStr(move: MoveChoice): String {
        return when (move) {
            MoveChoice.ROCK -> "Taş"
            MoveChoice.PAPER -> "Kağıt"
            else -> "Makas"
        }
    }

    @SuppressLint("SetTextI18n")
    fun generateAndCheckMove(playerMove: MoveChoice) {
        val computerMove: MoveChoice = MoveChoice.entries[(0..2).random()]
        val computerMoveStr: String = convertMoveToStr(computerMove)
        val playerMoveStr: String = convertMoveToStr(playerMove)

        var resultStr: String = ""

        if (playerMove == computerMove) {
            resultStr = "Berabere"
        }

        resultStr = when (playerMove) {
            MoveChoice.ROCK -> {
                when (computerMove) {
                    MoveChoice.PAPER -> "Bilgisayar kazandı"
                    else -> "Oyuncu kazandı"
                }
            }
            MoveChoice.PAPER -> {
                when (computerMove) {
                    MoveChoice.SCISSORS -> "Bilgisayar kazandı"
                    else -> "Oyuncu kazandı"
                }
            }
            else -> {
                when (computerMove) {
                    MoveChoice.ROCK -> "Bilgisayar kazandı"
                    else -> "Oyuncu kazandı"
                }
            }
        }

        val textView: TextView = findViewById(R.id.textView)
        textView.text = "Oyuncu hamlesi: $playerMoveStr | Bilgisayar hamlesi: $computerMoveStr | $resultStr"
    }

    fun rockPressed(view: View) {
        generateAndCheckMove(MoveChoice.ROCK)
    }

    fun paperPressed(view: View) {
        generateAndCheckMove(MoveChoice.PAPER)
    }

    fun scissorsPressed(view: View) {
        generateAndCheckMove(MoveChoice.SCISSORS)
    }
}
