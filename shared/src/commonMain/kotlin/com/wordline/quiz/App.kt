package com.wordline.quiz

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.worldline.quiz.WelcomeScreen
import com.wordline.quiz.data.QuizRepository
import kotlinx.serialization.Serializable

@Serializable
object WelcomeRoute

@Serializable
object QuizRoute

@Serializable
data class ScoreRoute(val score: Int, val questionSize: Int)

@Composable
fun App(
    navController: NavHostController = rememberNavController(),
    quizViewModel: QuizViewModel = QuizViewModel()
) {

    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = WelcomeRoute,
        ) {


            composable<WelcomeRoute>() {
                WelcomeScreen(
                    onStartButtonPushed = {
                        navController.navigate(route = QuizRoute)
                    }
                )
            }
            composable<QuizRoute>() {
                val questions by quizViewModel.questionState.collectAsState()
                QuestionScreen(
                    questions = questions,

                    onFinishButtonPushed = {
                            score: Int, questionSize: Int -> navController.navigate(route = ScoreRoute(score, questionSize))
                    }
                )
            }
            composable<ScoreRoute> { backStackEntry ->
                val scoreRoute: ScoreRoute = backStackEntry.toRoute<ScoreRoute>()
                ScoreScreen(
                    score = scoreRoute.score,
                    total = scoreRoute.questionSize,
                    onResetButtonPushed = {
                        navController.navigate(route = WelcomeRoute)
                    }
                )
            }
        }
    }
}


