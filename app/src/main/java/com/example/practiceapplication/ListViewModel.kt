package com.example.practiceapplication

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.widget.EditText
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import java.io.StringReader

class ListViewModel : ViewModel() {
    fun on_list_clicked(clicked_match: Match, nav_controller: NavController)
    {
        val match_index: Int = MatchesList.live_matches_list.value!!.indexOf(clicked_match)
        MatchesList.current_match_id = match_index
        nav_controller.navigate(R.id.to_info_fragment)
    }

    fun load_matches_list()
    {
        val matches_api = RetrofitClient.create_retrofit().create(RetrofitClient.api_client::class.java)
        viewModelScope.launch()
        {
            val result = matches_api.get_matches()
            MatchesList.matches_list.value = result.body()
        }
    }

    fun get_searched_list(search_team: String)
    {
        var new_matches_list: ArrayList<Match> = ArrayList(MatchesList.matches_list.value)
        for(match: Match in MatchesList.matches_list.value!!)
        {
            if ((match.HomeTeam != search_team) && (match.AwayTeam != search_team))
                new_matches_list.remove(match)
        }
        MatchesList.matches_list.value = new_matches_list
    }

    fun create_dialog(req_context: Context)
    {
        val dialog_builder: AlertDialog.Builder = AlertDialog.Builder(req_context)
        val edit_text: EditText = EditText(req_context)

        dialog_builder.setTitle("Search")
            .setMessage("Print team you want to search")
            .setView(edit_text)
            .setPositiveButton("Find", DialogInterface.OnClickListener()
            {
                    dialogInterface, i ->
                get_searched_list(edit_text.text.toString())
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener()
            {
                    dialogInterface, i ->
                load_matches_list()
            })
        dialog_builder.create().show()
    }

    object RetrofitClient
    {
        val api_url: String = "https://fixturedownload.com/"

        interface api_client
        {
            @GET("/feed/json/epl-2023")
            suspend fun get_matches(): Response<List<Match>>
        }

        fun create_retrofit(): Retrofit
        {
            val gson_converter: Converter.Factory = GsonConverterFactory.create()
            val retrofit: Retrofit = Retrofit.Builder().baseUrl(api_url)
                .addConverterFactory(gson_converter).build()
            return retrofit
        }
    }
}