package com.example.rokomaribookapp.utils.app

import android.app.Application
import com.example.rokomaribookapp.db.AppDatabase
import com.example.rokomaribookapp.utils.extentions.MockDataGenerator
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var database: AppDatabase

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()

        applicationScope.launch {
            // Generate Mock
            MockDataGenerator.generate(database)
        }
    }
}
