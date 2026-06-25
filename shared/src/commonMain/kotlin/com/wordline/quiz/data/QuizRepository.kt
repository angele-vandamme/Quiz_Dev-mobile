package com.wordline.quiz.data

import com.wordline.quiz.data.datasources.MockDataSource
import com.wordline.quiz.data.datasources.QuizApiDataSource

class QuizRepository  {
    private val mockDataSource = MockDataSource()
    private val quizApiDatasource = QuizApiDataSource()

    private suspend fun fetchQuiz(): List<Question> = quizApiDatasource.getAllQuestions().questions
    suspend fun updateQuiz():List<Question>{
        try {
            return fetchQuiz()
        } catch (e: Exception) {
            e.printStackTrace()
            return mockDataSource.generateDummyQuestionsList()
        }
    }
}