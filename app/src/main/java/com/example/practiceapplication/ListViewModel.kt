package com.example.practiceapplication

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
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

data class MatchItem(
    val MatchNumber: Int = 0,
    val RoundNumber: Int = 0,
    val DateUtc: String = "00000000",
    val Location: String = "default",
    val HomeTeam: String = "default",
    val AwayTeam: String = "default",
    val HomeTeamScore: Int = 0,
    val AwayTeamScore: Int = 0
)

class ListViewModel : ViewModel() {
    val first_test_item: Match = Match(0, "Godoters", "Unity guys",
        "Godoters", 23122024)
    val second_test_item: Match = Match(1, "Godoters", "Unity guys",
        "Godoters", 25122024)
    var items_list: Array<Match> = arrayOf(first_test_item, second_test_item)
    var matches_list: List<MatchItem> = emptyList()

    fun on_list_clicked(clicked_match: Match, nav_controller: NavController)
    {
        val match_id: Int = clicked_match.match_id
        nav_controller.navigate(R.id.to_info_fragment)
    }

    fun get_match_list(): String
    {
        var result_string: String = ""
        val matches_api = RetrofitClient.create_retrofit().create(RetrofitClient.api_client::class.java)
        GlobalScope.launch()
        {
            val json_reader: JsonReader = JsonReader(StringReader(""))
            val result = matches_api.get_matches()
            matches_list = result.body()!!.toList()
        }
        return result_string
    }

    object RetrofitClient
    {
        val api_url: String = "https://fixturedownload.com/"

        interface api_client
        {
            @GET("/feed/json/epl-2023")
            suspend fun get_matches(): Response<List<MatchItem>>
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