package com.example.nm_lab4_geoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nm_lab4_geoquiz.ui.theme.NM_lab4_GeoQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val num = rememberSaveable{ mutableIntStateOf(0) }
            val result = rememberSaveable{ mutableIntStateOf(0) }
            NM_lab4_GeoQuizTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding).fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AnswerMe(modifier = Modifier.fillMaxWidth(), num = num, result = result)
                    }
                }
            }
        }
    }
}

val questions = listOf(
    "Канберра - столица Австралии",
    "Тихий океан больше Атлантического океана",
    "Суэцкий канал соединяет Красное море и Индийский океан",
    "Река Нил исходит из Египта",
    "Река Амазонка - самая длинная река в Америке",
    "Озеро Байкал - старейшее и самое глубокое из пресноводных озер"
)
val answers = listOf(true, true, false, false, true, true)
fun answerfun(ans: Boolean, num: MutableIntState, result: MutableIntState) {
    if (ans == answers[num.intValue]) {
        result.intValue += 1
    }
}

@Composable
fun AnswerMe(modifier: Modifier, num: MutableIntState, result: MutableIntState) {
    val isnext = rememberSaveable{ mutableStateOf(false) }
    if (num.intValue >= questions.size) {
        Text(
            text = "Реузльтат: ${result.intValue} / ${questions.size}",
            modifier = modifier,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
    }
    if (num.intValue < questions.size) {
        Text(
            text = questions[num.intValue],
            modifier = modifier,
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
    Spacer(Modifier.height(20.dp))
    if (!isnext.value && num.intValue < questions.size) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button({ answerfun(true, num, result); isnext.value = true }) {
                Text(text = "Правда")
            }
            Button({ answerfun(false, num, result); isnext.value = true }) {
                Text(text = "Ложь")
            }
        }
    }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        if (isnext.value) {
            Button({ num.intValue += 1; isnext.value = false }, Modifier.fillMaxWidth(0.5f)) {
                Text(text = "Далее")
            }
        }
    }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        if (num.intValue >= questions.size) {
            Button({ num.intValue = 0; isnext.value = false; result.intValue = 0 }, Modifier.fillMaxWidth(0.5f)) {
                Text(text = "Заново")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val num = remember{ mutableIntStateOf(0) }
    val result = remember{ mutableIntStateOf(0) }
    NM_lab4_GeoQuizTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnswerMe(modifier = Modifier.fillMaxWidth(), num = num, result = result)
            }
        }
    }
}