package com.example.practiceapplication

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

class ListFragment : Fragment()
{
    companion object {
        fun newInstance() = ListFragment()
    }

    private val viewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.load_matches_list()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: ComposeView = ComposeView(requireContext())
        view.apply {
            setContent()
            {
                list_page_creating()
            }
        }
        return view
    }

    @Composable
    fun list_page_creating()
    {
        val matches_list = MatchesList.live_matches_list.observeAsState(emptyList())
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.main_background)),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.secondary_background)))
            {
                Spacer(modifier = Modifier.size(35.dp))
                Text(text = "Matches", fontSize = 24.sp,
                    modifier = Modifier.padding(horizontal = 10.dp),
                    fontFamily = FontFamily.SansSerif,
                    color = colorResource(id = R.color.main_text_color))
                IconButton(onClick = {viewModel.create_dialog(requireContext())})
                {
                    Image(painter = painterResource(id = R.drawable.search),
                        contentDescription = "SearchButton")
                }
            }
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally)
            {
                items(matches_list.value.size)
                {
                    index ->
                    generate_match_item(matches_list.value.get(index))
                }
            }
        }
    }

    @Composable fun generate_match_item(match: Match)
    {
        Surface(color = colorResource(id = R.color.main_item_color),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .shadow(5.dp, shape = RoundedCornerShape(15.dp)),
            shape = RoundedCornerShape(15.dp),
            onClick = {viewModel.on_list_clicked(match, findNavController())})
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center)
            {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(vertical = 15.dp))
                {
                    Box(modifier = Modifier
                        .width(125.dp)
                        .height(50.dp),
                        contentAlignment = Alignment.Center)
                    {
                        Text(text = match.HomeTeam,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily.SansSerif,
                            color = colorResource(id = R.color.white))
                    }
                    Image(painter = painterResource(id = R.drawable.versus),
                        contentDescription = "VersusIcon",
                        modifier = Modifier.size(80.dp))
//                    Text(text = "VS",
//                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 40.dp),
//                        fontSize = 25.sp,
//                        fontFamily = FontFamily.SansSerif,
//                        color = colorResource(id = R.color.white))
                    Box(modifier = Modifier
                        .width(125.dp)
                        .height(50.dp),
                        contentAlignment = Alignment.Center)
                    {
                        Text(
                            text = match.AwayTeam,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily.SansSerif,
                            color = colorResource(id = R.color.white)
                        )
                    }
                }
                Row(horizontalArrangement = Arrangement.Absolute.SpaceEvenly)
                {
                    Text(text = "Score: ${match.HomeTeamScore}",
                        modifier = Modifier.padding(vertical = 10.dp),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.SansSerif,
                        color = colorResource(id = R.color.white))
                    Spacer(modifier = Modifier.padding(horizontal = 25.dp))
                    Text(text = "Score: ${match.AwayTeamScore}",
                        modifier = Modifier.padding(vertical = 10.dp),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.SansSerif,
                        color = colorResource(id = R.color.white))
                }
                HorizontalDivider(thickness = 2.dp,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                    color = colorResource(id = R.color.main_text_color))
                Text(text = stringResource(id = R.string.date),
                    fontFamily = FontFamily.SansSerif,
                    color = colorResource(id = R.color.white))
                Text(
                    text = MatchesList.get_time(match),
                    modifier = Modifier.padding(vertical = 10.dp),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = colorResource(id = R.color.white)
                )
            }
        }
    }
}