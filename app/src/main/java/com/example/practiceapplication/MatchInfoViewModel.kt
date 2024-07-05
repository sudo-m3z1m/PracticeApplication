package com.example.practiceapplication

import androidx.lifecycle.ViewModel

class MatchInfoViewModel : ViewModel() {
    var match: Match = Match()

    fun set_match(new_match: Match)
    {
        match = new_match
    }
}