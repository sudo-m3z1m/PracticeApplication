package com.example.practiceapplication

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
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
    public fun generate_match_item() //Need to make this in Item
    {
        Surface(color = colorResource(id = R.color.teal_700),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .shadow(5.dp, shape = RoundedCornerShape(15.dp)),
            shape = RoundedCornerShape(15.dp),
            onClick = { surface_on_click() })
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center)
            {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center)
                {
                    Text(text = first_team, modifier = Modifier.padding(horizontal = 10.dp), fontSize = 20.sp)
<<<<<<< HEAD
                    Text(text = "VS", modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp), fontSize = 25.sp)
=======
                    Text(text = "VS", modifier = Modifier.padding(horizontal = 10.dp, vertical = 40.dp), fontSize = 25.sp)
>>>>>>> 076dfb32ba40bfa8194d6beffc0206ea2869dfa2
                    Text(text = second_team, modifier = Modifier.padding(horizontal = 10.dp), fontSize = 20.sp)
                }
            }
        }
    }

    fun surface_on_click()
    {
        Log.i("Hello world", "Fuck")
    }
}