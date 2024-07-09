package com.example.practiceapplication

import android.app.AlertDialog
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally)
        {
            Row(horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp))
            {
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Matches", fontSize = 24.sp, modifier = Modifier.weight(1f))
                IconButton(onClick = {create_dialog()})
                {
                    Image(painter = painterResource(id = R.drawable.ic_launcher_background),
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
        Surface(color = colorResource(id = R.color.teal_700),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .shadow(5.dp, shape = RoundedCornerShape(15.dp)),
            shape = RoundedCornerShape(15.dp),
            onClick = {viewModel.on_list_clicked(match, findNavController())})
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center)
            {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center)
                {
                    Text(text = match.HomeTeam, modifier = Modifier.padding(horizontal = 10.dp), fontSize = 20.sp)
                    Text(text = "VS", modifier = Modifier.padding(horizontal = 10.dp, vertical = 40.dp), fontSize = 25.sp)
                    Text(text = match.AwayTeam, modifier = Modifier.padding(horizontal = 10.dp), fontSize = 20.sp)
                }
            }
        }
    }

    fun create_dialog()
    {
        val dialog_builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        val edit_text: EditText = EditText(requireContext())

        dialog_builder.setTitle("Search")
            .setMessage("Print team you want to search")
            .setView(edit_text)
            .setPositiveButton("Find", DialogInterface.OnClickListener()
            {
                dialogInterface, i ->
                viewModel.get_searched_list(edit_text.text.toString())
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener()
            {
                dialogInterface, i ->
                viewModel.load_matches_list()
            })
        dialog_builder.create().show()
    }
}