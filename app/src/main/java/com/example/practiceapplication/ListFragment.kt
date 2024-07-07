package com.example.practiceapplication

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.fragment.findNavController

class ListFragment : Fragment()
{
    companion object {
        fun newInstance() = ListFragment()
    }

    private val viewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(text = "Matches", fontSize = 24.sp, modifier = Modifier.padding(30.dp))
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally)
            {
                items(viewModel.items_list.size)
                {
                        index ->
                        generate_match_item(viewModel.items_list[index])
                }
            }
        }
    }

    @Composable fun generate_match_item(match: Match)
    {
        Surface(color = colorResource(id = R.color.teal_700),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .shadow(5.dp, shape = RoundedCornerShape(15.dp)),
            shape = RoundedCornerShape(15.dp),
            onClick = {viewModel.get_match_list()})
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center)
            {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center)
                {
                    Text(text = match.first_team, modifier = Modifier.padding(horizontal = 10.dp), fontSize = 20.sp)
                    Text(text = "VS", modifier = Modifier.padding(horizontal = 10.dp, vertical = 40.dp), fontSize = 25.sp)
                    Text(text = match.second_team, modifier = Modifier.padding(horizontal = 10.dp), fontSize = 20.sp)
                }
            }
        }
    }
}