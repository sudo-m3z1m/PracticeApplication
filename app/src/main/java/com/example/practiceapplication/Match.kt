package com.example.practiceapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Match(first_team: String = "DEFAULT", second_team: String = "DEFAULT",
            result: String = "DEFAULT", date: Int = 0)
{
    private val first_team: String = first_team
    private val second_team: String = second_team
    private val result: String = result
    private val date: Int = date

    @Composable
    public fun generate_match_item()
    {
        Surface(color = colorResource(id = R.color.teal_700),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .shadow(15.dp, shape = RoundedCornerShape(15.dp)),
            shape = RoundedCornerShape(15.dp),
            onClick = {})
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center)
            {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center)
                {
                    Text(text = first_team, modifier = Modifier.padding(horizontal = 10.dp), fontSize = 20.sp)
                    Text(text = "VS", modifier = Modifier.padding(horizontal = 10.dp), fontSize = 25.sp)
                    Text(text = second_team, modifier = Modifier.padding(horizontal = 10.dp), fontSize = 20.sp)
                }
                Text(text = "Win result: $result", modifier = Modifier.padding(10.dp), fontSize = 20.sp)
                Text(text = "Match date: $date", modifier = Modifier.padding(5.dp), fontSize = 20.sp)
            }
        }
    }
}