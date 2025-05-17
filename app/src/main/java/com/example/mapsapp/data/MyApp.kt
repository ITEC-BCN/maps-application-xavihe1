package com.example.mapsapp.data

import android.app.Application

class MyApp: Application() {
    companion object {
        lateinit var database: MySupabaseClient
    }
    override fun onCreate() {
        super.onCreate()
        database = MySupabaseClient(
            supabaseUrl = "https://hjlixwdqdvsmikldgxna.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhqbGl4d2RxZHZzbWlrbGRneG5hIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDc0OTc4NDksImV4cCI6MjA2MzA3Mzg0OX0.9ef3wqjkEHIFeCoLPyYJqsgDJvcWtNpPHL0wKVXDPi4"
        )
    }
}
