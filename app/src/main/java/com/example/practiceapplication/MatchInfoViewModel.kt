package com.example.practiceapplication

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class MatchInfoViewModel : ViewModel() {
    var match: Match = Match()

    fun set_new_match(match_index: Int)
    {
        match = MatchesList.live_matches_list.value!!.get(match_index)
    }
}