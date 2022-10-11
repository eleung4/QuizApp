package com.example.quizapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    companion object{
        val TAG = "MainActivity"
    }

    private lateinit var quiz : Quiz
    lateinit var buttonTrue : Button
    lateinit var buttonFalse : Button
    lateinit var textView : TextView
    lateinit var textView2 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadQuestions()
        wireWidgets()

        //get the first question, set up the textViews
        val scoreText = getString(R.string.main_score)
        textView_score.text = "$scoreText ${quiz.score}"


        buttonTrue.setOnClickListener {
            quiz.checkAnswer(true)
        }

        buttonFalse.setOnClickListener {
            quiz.checkAnswer(false)
        }

    }


    private fun wireWidgets() {
        buttonTrue = findViewById(R.id.button_MainActivity_true)
        buttonFalse = findViewById(R.id.button_MainActivity_false)
        textView = findViewById(R.id.textView_MainActivity_question)
        textView2 = findViewById(R.id.textView2_MainActivity_points)
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

    }


}
