package com.example.practiceapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MatchInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

@Preview(showBackground = true)
@Composable
fun match_info_preview()
{
    paste_match_info()
}

@Composable
fun paste_match_info(match_id: Int = 0)
{
    var match: Match = Match() //Need to use MatchID btw :>
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top)
    {
        Text(text = stringResource(id = R.string.match),
            modifier = Modifier.padding(15.dp),
            fontSize = 30.sp)
        Surface(color = colorResource(id = R.color.teal_700),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 10.dp,
                    vertical = 20.dp
                )
                .shadow(10.dp, shape = RoundedCornerShape(15.dp)),
            shape = RoundedCornerShape(15.dp))
        {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center)
            {
                Column(horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = stringResource(id = R.string.match))
                    Text(text = "First_default_team",
                        color = colorResource(id = R.color.white))
                }
                Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = stringResource(id = R.string.match),
                    modifier = Modifier.padding(vertical = 30.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = stringResource(id = R.string.match))
                    Text(text = "Second_default_team", //Need to do smthg with this text
                        color = colorResource(id = R.color.white))
                }
            }
        }
        Surface(color = colorResource(id = R.color.teal_700),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .shadow(10.dp, shape = RoundedCornerShape(15.dp)),
            shape = RoundedCornerShape(15.dp))
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally)
            {
                Text(text = stringResource(id = R.string.result),
                    fontSize = 35.sp,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.padding(5.dp))
                Text(text = "Godoters",
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.teal_200),
                    modifier = Modifier.padding(10.dp))
                HorizontalDivider(modifier = Modifier.padding(horizontal = 25.dp))
                Text(text = stringResource(id = R.string.date),
                    fontSize = 35.sp,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.padding(5.dp))
                Text(text = "22.06.2024",
                    fontSize = 20.sp,
                    color = Color.Cyan,
                    modifier = Modifier.padding(10.dp))
            }
        }
    }
}