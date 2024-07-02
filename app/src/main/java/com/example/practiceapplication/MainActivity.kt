package com.example.practiceapplication

import android.content.ClipData.Item
import android.os.Bundle
import android.widget.Space
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
import androidx.compose.runtime.Composable
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

class Match(first_team: String = "DEFAULT", second_team: String = "DEFAULT",
            result: String = "DEFAULT", date: Int = 0)
{
    public val first_team: String = first_team
    public val second_team: String = second_team
    public val result: String = result
    public val date: Int = date
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeApplicationTheme {
                Surface()
                {
//                    list_page_creating(generate_matches())
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
    var array: Array<Match> = arrayOf(test_match)
    list_page_creating(array)
}

@Composable
fun list_page_creating(items_list: Array<Match>)
{
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally)
    {
        Spacer(modifier = Modifier.height(25.dp))
        Text(text = "Matches", fontSize = 24.sp)
        Spacer(Modifier.height(30.dp))
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally)
        {
            items(items_list.size)
            {
                index ->
                    Surface(color = colorResource(id = R.color.teal_700),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .shadow(15.dp, shape = RoundedCornerShape(15.dp)),
                        shape = RoundedCornerShape(15.dp))
                    {
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center)
                        {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center)
                            {
                                Text(text = items_list[index].first_team, modifier = Modifier.padding(horizontal = 10.dp), fontSize = 20.sp)
                                Text(text = "VS", modifier = Modifier.padding(horizontal = 10.dp), fontSize = 25.sp)
                                Text(text = items_list[index].second_team, modifier = Modifier.padding(horizontal = 10.dp), fontSize = 20.sp)
                            }
                            Text(text = "Win result: ${items_list[index].result}", modifier = Modifier.padding(10.dp), fontSize = 20.sp)
                            Text(text = "Match date: ${items_list[index].date}", modifier = Modifier.padding(5.dp), fontSize = 20.sp)
                        }
                    }
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