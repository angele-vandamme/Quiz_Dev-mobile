package com.wordline.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wordline.quiz.data.Question

@Composable
fun QuestionScreen(
    questions: List<Question>,
    onFinishButtonPushed: (Int, Int) -> Unit
) {
    var questionProgress by remember { mutableIntStateOf(0) }
    var selectedAnswer by remember { mutableIntStateOf(1) }
    var score by remember { mutableIntStateOf(0) }
    val currentQuestion = questions[questionProgress]

    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.padding(60.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(
                    modifier = Modifier.padding(all = 10.dp),
                    text = questions[questionProgress].label,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        Column(modifier = Modifier.selectableGroup()) {
            questions[questionProgress].answers.forEach { answer ->
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        modifier = Modifier.padding(end = 16.dp),
                        selected = (selectedAnswer == answer.id),
                        onClick = { selectedAnswer = answer.id },
                    )
                    Text(text = answer.label)
                }
            }
        }
        currentQuestion.indice?.let { indice ->
            Card(
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.padding(60.dp)
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = indice.label,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                modifier = Modifier.padding(bottom = 20.dp),
                onClick = {
                    if (selectedAnswer == questions[questionProgress].correctAnswerId) {
                        score++
                    }
                    if (questionProgress < questions.size - 1) {
                        questionProgress++
                        selectedAnswer = 1
                    } else {
                        onFinishButtonPushed(score, questions.size)
                    }
                }
            ) {
                if (questionProgress < questions.size - 1) {
                    NextOrDoneButton(Icons.AutoMirrored.Filled.ArrowForward, "Next")
                } else {
                    NextOrDoneButton(Icons.Filled.Done, "Done")
                }
            }
            LinearProgressIndicator(
                progress = { (questionProgress + 1) / questions.size.toFloat() },
                modifier = Modifier.fillMaxWidth().height(20.dp),
                color = ProgressIndicatorDefaults.linearColor,
                trackColor = ProgressIndicatorDefaults.linearTrackColor,
                strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
            )
        }
    }
}

@Composable
fun NextOrDoneButton(iv: ImageVector, label: String) {
    Icon(
        iv,
        contentDescription = null,
        Modifier.padding(end = 15.dp)
    )
    Text(label)
}
