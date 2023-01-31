package com.z0o0a.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.z0o0a.composetest.ui.theme.ComposeTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MessageCard(Message("Android", "Jetpack Compose"))
                }
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.temp_img),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)  // 이미지 사이즈
                .clip(CircleShape)  // 이미지 자르기
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)  // 이미지 테두리
        )

        Spacer(modifier = Modifier.width(8.dp)) // 이미지랑 오른쪽에 텍스트들 사이 여백

        Column {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,  // 텍스트 이미지
                style = MaterialTheme.typography.subtitle2  // 텍스트 스타일(텍스트 글꼴)
            )

            Spacer(modifier = Modifier.height(4.dp))  // 텍스트 사이 여백

            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview
@Composable
fun DefaultPreview() {
    ComposeTestTheme {
        Conversation(SampleData.conversationSample)
    }
}