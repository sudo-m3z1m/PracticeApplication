package com.example.practiceapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}

object MatchesList
{
    var matches_list = MutableLiveData<List<Match>>()
    var live_matches_list: LiveData<List<Match>> = matches_list

    var current_match_id: Int = 0
}