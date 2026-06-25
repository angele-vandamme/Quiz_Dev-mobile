package com.wordline.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wordline.quiz.data.Indice
import com.wordline.quiz.data.Question
import com.wordline.quiz.data.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {
    private var quizRepository: QuizRepository = QuizRepository()
    private var _questionState=  MutableStateFlow(listOf<Question>())
    val questionState get() = _questionState

    /* Can be replaced with explicit backing fields
    val questionState : StateFlow<List<Question>>
       field =  MutableStateFlow(listOf<Question>())
    -> in build.gradle.kts : sourceSets.all { languageSettings.enableLanguageFeature("ExplicitBackingFields") }
    */

    init {
        getQuestionQuiz()
    }

    private fun getQuestionQuiz() {
        viewModelScope.launch(Dispatchers.Default) {
            _questionState.update {
                quizRepository.updateQuiz()
            }
        }
    }
}
