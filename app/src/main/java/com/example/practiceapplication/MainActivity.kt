package com.example.practiceapplication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.main_activity)
    }
}

object MatchesList
{
    var matches_list = MutableLiveData<List<Match>>()
    var live_matches_list: LiveData<List<Match>> = matches_list

    var current_match_id: Int = 0

    fun get_time(match: Match): String
    {
        val utc_date_format: String = "yyyy-MM-dd HH:mm:ss"
        val new_date_format: String = "dd MMM yyyy HH:mm"

        var date_formater = SimpleDateFormat(utc_date_format, Locale.ENGLISH) //Need to make parse to Date in match btw
        date_formater.timeZone = TimeZone.getTimeZone("UTC")
        val utc_date: Date = date_formater.parse(match.DateUtc)

        date_formater = SimpleDateFormat(new_date_format, Locale.ENGLISH)
        date_formater.timeZone = TimeZone.getDefault()
        val new_date: String = date_formater.format(utc_date)

        return new_date
    }
}