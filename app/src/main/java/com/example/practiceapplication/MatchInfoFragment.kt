package com.example.practiceapplication

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MatchInfoFragment : Fragment() {
    companion object {
        fun newInstance() = MatchInfoFragment()
    }

    private val viewModel: MatchInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: ComposeView = ComposeView(requireContext())
        view.apply()
        {
            setContent()
            {
                paste_match_info(viewModel.match)
            }
        }
        return view
    }
    @Composable
    fun paste_match_info(match: Match)
    {
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
                shape = RoundedCornerShape(15.dp)
            )
            {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center)
                {
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .height(120.dp)
                            .width(120.dp))
                    {
                        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = stringResource(id = R.string.match)
                        )
                        Text(text = match.first_team,
                            color = colorResource(id = R.color.white)
                        )
                    }
                    Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = stringResource(id = R.string.match),
                        modifier = Modifier.padding(vertical = 30.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .height(120.dp)
                            .width(120.dp))
                    {
                        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = stringResource(id = R.string.match)
                        )
                        Text(text = match.second_team,
                            color = colorResource(id = R.color.white)
                        )
                    }
                }
            }
            Surface(color = colorResource(id = R.color.teal_700),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .shadow(10.dp, shape = RoundedCornerShape(15.dp)),
                shape = RoundedCornerShape(15.dp)
            )
            {
                Column(horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Text(text = stringResource(id = R.string.result),
                        fontSize = 35.sp,
                        color = colorResource(id = R.color.white),
                        modifier = Modifier.padding(5.dp))
                    Text(text = match.result,
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.teal_200),
                        modifier = Modifier.padding(10.dp))
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 25.dp))
                    Text(text = stringResource(id = R.string.date),
                        fontSize = 35.sp,
                        color = colorResource(id = R.color.white),
                        modifier = Modifier.padding(5.dp))
                    Text(text = "${match.date}",
                        fontSize = 20.sp,
                        color = Color.Cyan,
                        modifier = Modifier.padding(10.dp))
                }
            }
        }
    }
}