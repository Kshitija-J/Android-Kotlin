package com.example.nfl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TeamDetailVM(teamId : String): ViewModel() {
    private val nflRepo = NFLRepo.get()

    private val _team: MutableStateFlow<NFLTeam?> = MutableStateFlow(null)
    val team: StateFlow<NFLTeam?> = _team.asStateFlow()

    init {
        viewModelScope.launch {
            _team.value = nflRepo.fetchById(teamId)

        }
    }


}



class TeamDetailVMFactory (private val teamId: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TeamDetailVM(teamId) as T
    }
}