package com.example.practiceapplication

import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {
    val first_test_item: Match = Match("Godoters", "Unity guys",
        "Godoters", 23122024)
    val second_test_item: Match = Match("Godoters", "Unity guys",
        "Godoters", 25122024)
    var items_list: Array<Match> = arrayOf(first_test_item, second_test_item)
}