package com.example.practiceapplication

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
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
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Headers
import java.io.IOException
import java.io.StringReader
import kotlin.jvm.Throws

class ListViewModel : ViewModel() {
    fun on_list_clicked(clicked_match: Match, nav_controller: NavController) {
        val match_index: Int = MatchesList.live_matches_list.value!!.indexOf(clicked_match)
        val action = ListFragmentDirections.toInfoFragment(match_index)
        nav_controller.navigate(action)
    }

    fun load_matches_list(context: Context)
    {
        if(!has_connection(context))
            return create_internet_warning(context)

        val matches_api = RetrofitClient.create_retrofit(context)
            .create(RetrofitClient.api_client::class.java)

        viewModelScope.launch()
        {
            val result = matches_api.get_matches()
            Log.d("result list", result.body().toString())
            MatchesList.matches_list.value = result.body()
        }
    }

    fun get_searched_list(search_team: String)
    {
        var new_matches_list: ArrayList<Match> = ArrayList(MatchesList.matches_list.value)
        for (match: Match in MatchesList.matches_list.value!!) {
            if ((match.HomeTeam != search_team) && (match.AwayTeam != search_team))
                new_matches_list.remove(match)
        }
        MatchesList.matches_list.value = new_matches_list
    }

    object RetrofitClient {
        val api_url: String = "https://fixturedownload.com/"
        val cache_size: Long = (10 * 1024 * 1024).toLong()

        interface api_client {
            @Headers("Cache-control: public, max-age=3600")
            @GET("/feed/json/epl-2023")
            suspend fun get_matches(): Response<List<Match>>
        }

        fun create_retrofit(context: Context): Retrofit {
            val cache: Cache = Cache(context.cacheDir, cache_size)
            val http_client: OkHttpClient = OkHttpClient.Builder().cache(cache).build()

            val gson_converter: Converter.Factory = GsonConverterFactory.create()
            val retrofit: Retrofit = Retrofit.Builder().baseUrl(api_url)
                .addConverterFactory(gson_converter).client(http_client).build()

            return retrofit
        }
    }

    fun create_internet_warning(req_context: Context)
    {
        var dialog_builder: AlertDialog.Builder = AlertDialog.Builder(req_context)
        dialog_builder.setTitle("Connection error")
            .setMessage("Oops! We lost internet connection! Try to connect to the internet and retry.")
            .setPositiveButton("Ok", null)
            .create().show()
    }

    fun create_dialog(req_context: Context)
    {
        val dialog_builder: AlertDialog.Builder = AlertDialog.Builder(req_context)
        val edit_text: EditText = EditText(req_context)

        dialog_builder.setTitle("Search")
            .setMessage("Print team you want to search")
            .setView(edit_text)
            .setPositiveButton("Find", DialogInterface.OnClickListener()
            { dialogInterface, i ->
                get_searched_list(edit_text.text.toString())
            })
        dialog_builder.create().show()
    }

    fun has_connection(context: Context): Boolean
    {
        val connectivity_manager: ConnectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivity_manager.activeNetwork != null
    }

}