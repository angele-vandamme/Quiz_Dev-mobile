package com.wordline.quiz.data.datasources

import com.wordline.quiz.data.Quiz
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val globalHttpClient = HttpClient {
    engine {

    }

    install(ContentNegotiation) {
        json(
            contentType = ContentType.Text.Plain, // because Github is not returning an 'application/json' header
            json = Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
    }
}

class QuizApiDataSource {
    private val httpClient = globalHttpClient
    suspend fun getAllQuestions(): Quiz {
        return httpClient.get("https://raw.githubusercontent.com/angele-vandamme/Quiz_Dev-mobile/refs/heads/main/quiz.json").body()
    }
}