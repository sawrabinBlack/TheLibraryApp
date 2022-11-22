package com.example.thelibrary

import android.app.Application
import android.util.Log
import com.example.thelibrary.data.model.LibraryModelImpl
import com.example.thelibrary.delegate.LibraryViewPodDelegate

class TheLibraryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LibraryModelImpl.initDatabase(context = applicationContext)
        Log.println(Log.INFO,"databaseTest","ok")
    }
}