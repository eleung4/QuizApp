package com.example.quizapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    companion object{
        val TAG = "MainActivity"
    }

    private lateinit var quiz : Quiz
    lateinit var buttonTrue : Button
    lateinit var buttonFalse : Button
    lateinit var textView_question : TextView
    lateinit var textView_points : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadQuestions()
        wireWidgets()

        //get the first question, set up the textViews
        val scoreText = getString(R.string.main_score)
        textView_points.text = "$scoreText ${quiz.currentScore}"

        buttonTrue.setOnClickListener {
            quiz.checkAnswer(true)
            quiz.nextQuestion()
            nextQuestion2()
            textView_points.text = "$scoreText ${quiz.currentScore}"


        }


        buttonFalse.setOnClickListener {
            quiz.checkAnswer(false)
            quiz.nextQuestion()
            nextQuestion2()
            textView_points.text = "$scoreText ${quiz.currentScore}"
            Toast.makeText( this, "u wrong", Toast.LENGTH_SHORT).show()

        }

    }

    //see if check question can be used as an if statement, stop the crashes after 5 questions


    private fun nextQuestion2() {
        quiz.numQuestion++
        quiz.currentScore++
        textView_question.text = quiz.questions[quiz.numQuestion].question
    }


    private fun wireWidgets() {
        buttonTrue = findViewById(R.id.button_MainActivity_true)
        buttonFalse = findViewById(R.id.button_MainActivity_false)
        textView_question = findViewById(R.id.textView_MainActivity_question)
        textView_question.text = "${quiz.currentQuestion}"
        textView_points = findViewById(R.id.textView2_MainActivity_points)

    }

    private fun loadQuestions() {
        val inputStream = resources.openRawResource(R.raw.questions)

        val jsonString = inputStream.bufferedReader().use {
            // the last line of the use function is returned
            it.readText()
        }
        Log.d(TAG, "onCreate: $jsonString")

        // alternative way of writing the above line with the use lambda
//        val bufferedReader = inputStream.bufferedReader()
//        val jsonString = bufferedReader.readText()

        val gson = Gson()
        // gson needs to know what kind of list you are converting to
        // typetoken tells gson it will be a List<Question>

        val type = object : TypeToken<List<Question>>() { }.type
        val questions = gson.fromJson<List<Question>>(jsonString, type)
        quiz = Quiz(questions)

    }


}
