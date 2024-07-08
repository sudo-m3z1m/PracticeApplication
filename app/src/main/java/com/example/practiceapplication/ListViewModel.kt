package com.example.practiceapplication

import android.util.Log
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