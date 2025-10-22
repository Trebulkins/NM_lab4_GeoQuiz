package com.example.nm_lab4_geoquiz

import android.os.Bundle
import android.util.MutableInt
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.nm_lab4_geoquiz.ui.theme.NM_lab4_GeoQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val num = remember{ mutableIntStateOf(0) }
            val result = remember{ mutableIntStateOf(0) }
            NM_lab4_GeoQuizTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(Modifier.padding(innerPadding)) {
                        Text(
                            text="Вырос вопрос",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        AnswerMe(modifier = Modifier.fillMaxWidth(), num = num.value, result = result)
                    }
                }
            }
        }
    }
}

val questions = listOf<String>(
    "Канберра - столица Австралии",
    "Тихий океан больше Атлантического океана",
    "Суэцкий канал соединяет Красное море и Индийский океан",
    "Река Нил исходит из Египта",
    "Река Амазонка - самая длинная река в Америке",
    "Озеро Байкал - старейшее и самое глубокое из пресноводных озер"
)
val answers = listOf<Boolean>(true, true, false, false, true, true)
fun answerfun(ans: Boolean, num: Int, result: MutableIntState) {
    if (ans == answers[num]) {
        result.intValue += 1
    }
}

@Composable
fun AnswerMe(modifier: Modifier, num: Int, result: MutableIntState) {
    Column(modifier) {
        Text(
            text = questions[num],
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button({ answerfun(true, num, result) }) {
                Text(text = "Правда")
            }
            Button({ answerfun(false, num, result) }) {
                Text(text = "Ложь")
            }
        }
        Button({ answerfun(true, num, result) }) {
            Text(text = "Правда")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NM_lab4_GeoQuizTheme {
        AnswerMe(modifier = Modifier.fillMaxSize(), num = 0, result = mutableIntStateOf(0))
    }
}