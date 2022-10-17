package com.example.quizapp

import android.widget.Toast

class Quiz(val questions: List<Question>) {
    //variables to track score, current question

    //functions to check if there's another question,
    //give the next question, check the answer
    //give the final score, reset the quiz?, shuffle questions?

    var currentScore = 0
    var numQuestion = 0
    var currentQuestion = questions[numQuestion].question

    fun checkAnswer(answer: Boolean): Unit {
        if (answer.equals(questions)) {
            currentScore++

        }
        else {

        }
        numQuestion += 1
    }

    fun nextQuestion() : Boolean{
        return numQuestion <9




    }

    fun finalScore() : String {
        return "you got: "; "$currentScore"
    }


}