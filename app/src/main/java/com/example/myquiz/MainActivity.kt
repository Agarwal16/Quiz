package com.example.myquiz

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var choicesRadioGroup: RadioGroup
    private lateinit var nextButton: Button
    private lateinit var timerTextView: TextView

    private var questions: List<Question> = listOf()
    private var currentQuestionIndex = 0
    private var correctAnswers = 0
    private var timer: CountDownTimer? = null
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.questionTextView)
        choicesRadioGroup = findViewById(R.id.choicesRadioGroup)
        nextButton = findViewById(R.id.nextButton)
        timerTextView = findViewById(R.id.timerTextView)

        preferences = getSharedPreferences("quiz_preferences", MODE_PRIVATE)
        loadQuestions()
        restoreState()
        startTimer()
        displayQuestion()

        nextButton.setOnClickListener {
            checkAnswer()
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                displayQuestion()
            } else {
                endQuiz()
            }
        }
    }

    private fun loadQuestions() {
        val inputStream = assets.open("questions.json")
        val reader = InputStreamReader(inputStream)
        val type = object : TypeToken<List<Question>>() {}.type
        questions = Gson().fromJson(reader, type)
    }

    private fun startTimer() {
        val totalTime = 10 * 60 * 1000L
        timer = object : CountDownTimer(totalTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                timerTextView.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                endQuiz()
            }
        }.start()
    }

    private fun displayQuestion() {
        val question = questions[currentQuestionIndex]
        val questionNumber = currentQuestionIndex + 1
        questionTextView.text = "Question $questionNumber: ${question.question}"
        choicesRadioGroup.removeAllViews()
        for (choice in question.choices) {
            val radioButton = RadioButton(this)
            radioButton.text = choice
            choicesRadioGroup.addView(radioButton)
        }
        // Change button text to "Submit" if it's the last question
        if (currentQuestionIndex == questions.size - 1) {
            nextButton.text = "Submit"
        } else {
            nextButton.text = "Next"
        }
    }

    private fun checkAnswer() {
        val selectedId = choicesRadioGroup.checkedRadioButtonId
        if (selectedId != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedId)
            val selectedAnswer = selectedRadioButton.text.toString()
            if (selectedAnswer == questions[currentQuestionIndex].answer) {
                correctAnswers++
            }
        }
    }

    private fun endQuiz() {
        timer?.cancel()
        showQuizEndDialog()
    }

    private fun showQuizEndDialog() {
        AlertDialog.Builder(this)
            .setTitle("Quiz Completed")
            .setMessage("The quiz is over.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                navigateToResultScreen()
            }
            .show()
    }

    private fun navigateToResultScreen() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("correctAnswers", correctAnswers)
        intent.putExtra("totalQuestions", questions.size)
        startActivity(intent)
        finish()
    }

    override fun onPause() {
        super.onPause()
        saveState()
    }

    private fun saveState() {
        val editor = preferences.edit()
        editor.putInt("currentQuestionIndex", currentQuestionIndex)
        editor.putInt("correctAnswers", correctAnswers)
        editor.apply()
    }

    private fun restoreState() {
        currentQuestionIndex = preferences.getInt("currentQuestionIndex", 0)
        correctAnswers = preferences.getInt("correctAnswers", 0)
    }
}