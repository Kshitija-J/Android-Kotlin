package com.example.nfl

import android.app.Application

class NFLTeamApp : Application() {
    override fun onCreate() {
        super.onCreate()
        NFLRepo.initialize()
    }
}