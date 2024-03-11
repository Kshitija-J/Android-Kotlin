package com.example.nfl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "NFLListViewModel"
class NFLTeamListViewModel : ViewModel() {
    private val nflRepo: NFLRepo = NFLRepo.get()
    private val _nflFlow: MutableStateFlow<List<NFLTeam>> = MutableStateFlow(emptyList())
    val nflFlow: StateFlow<List<NFLTeam>>
        get() = _nflFlow.asStateFlow()

    init {
        Log.d(TAG, "init started")
        viewModelScope.launch {
            nflRepo.fetchCrimes().collect { teams ->
                _nflFlow.value = teams
            }
        }
    }
}