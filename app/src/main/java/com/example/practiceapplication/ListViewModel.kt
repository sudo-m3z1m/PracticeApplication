package com.example.practiceapplication

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

class ListViewModel : ViewModel() {
    val first_test_item: Match = Match(0, "Godoters", "Unity guys",
        "Godoters", 23122024)
    val second_test_item: Match = Match(1, "Godoters", "Unity guys",
        "Godoters", 25122024)
    var items_list: Array<Match> = arrayOf(first_test_item, second_test_item)

    fun on_list_clicked(clicked_match: Match, nav_controller: NavController)
    {
        val match_id: Int = clicked_match.match_id
        nav_controller.navigate(R.id.to_info_fragment)

    }
}