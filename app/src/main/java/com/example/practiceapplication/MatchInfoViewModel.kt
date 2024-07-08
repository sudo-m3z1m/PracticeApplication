package com.example.practiceapplication

import androidx.lifecycle.ViewModel

class MatchInfoViewModel : ViewModel() {
    var match: Match = Match()

    fun set_new_match(match_index: Int)
    {
        match = MatchesList.live_matches_list.value!!.get(match_index)
    }
}