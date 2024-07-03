package com.example.practiceapplication

import android.content.ClipData.Item
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practiceapplication.ui.theme.PracticeApplicationTheme
import kotlin.time.Duration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeApplicationTheme {
                Surface()
                {
                    val test_match: Match = Match("Godoters", "Unity guys",
                        "Godoters", 22)
                    val another_test_match: Match = Match("Godoters", "Unity guys",
                        "Godoters", 22)
                    var array: Array<Match> = arrayOf(test_match, another_test_match)
                    list_page_creating(array)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ready_preview()
{
    val test_match: Match = Match("Godoters", "Unity guys",
        "Godoters", 22)
    val another_test_match: Match = Match("Godoters", "Unity guys",
        "Godoters", 22)
    var array: Array<Match> = arrayOf(test_match, another_test_match)
    list_page_creating(array)
}

@Composable
fun list_page_creating(items_list: Array<Match>)
{
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = "Matches", fontSize = 24.sp, modifier = Modifier.padding(30.dp))
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally)
        {
            items(items_list.size)
            {
                index ->
                    items_list[index].generate_match_item()
            }
        }
    }
}

fun generate_matches(): Array<Match>
{
    var items_list: Array<Match> = emptyArray()
    for (item_index in (1..5))
    {
        val item: Match = Match(first_team = "Godoters!", second_team = "UnityGuys",
            result = "Godoters", date = item_index)
        items_list[item_index] = item
    }

    return items_list
}