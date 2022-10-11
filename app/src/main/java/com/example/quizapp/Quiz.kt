package com.example.quizapp

class Quiz(val questions: List<Question>) {
    //variables to track score, current question

    //functions to check if there's another question,
    //give the next question, check the answer
    //give the final score, reset the quiz?, shuffle questions?

    var currentScore = 0
    var numQuestions = 0

    fun checkAnswer(answer: Boolean): Unit {
        if (answer.equals(questions)) {
            currentScore++

        }
        else {

        }
        numQuestions += 1
    }

}