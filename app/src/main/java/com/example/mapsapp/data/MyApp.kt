package com.example.mapsapp.data

import android.app.Application
import io.github.jan.supabase.BuildConfig
import io.github.jan.supabase.annotations.SupabaseInternal

class MyApp: Application() {
    companion object {
        lateinit var database: MySupabaseClient
    }

    override fun onCreate() {
        super.onCreate()
        database = MySupabaseClient()
    }
}
