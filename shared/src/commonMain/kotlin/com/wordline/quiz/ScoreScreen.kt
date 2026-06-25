package com.wordline.quiz

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScoreScreen(score: Int, total: Int, onResetButtonPushed: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(10.dp),
            colors = androidx.compose.material3.CardDefaults.cardColors(
                containerColor = Color.Green
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    fontSize = 15.sp,
                    text = "Votre score est de :",
                )
                Text(
                    fontSize = 30.sp,
                    text = "$score / $total",
                )
                Button(
                    modifier = Modifier.padding(top = 20.dp),
                    onClick = onResetButtonPushed
                ) {
                    Text(text = "Recommencer le Quiz")
                }
            }
        }
    }
}
