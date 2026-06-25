package com.wordline.quiz.data

@kotlinx.serialization.Serializable
data class Quiz(
    var questions: List<Question>,
)

