package com.alfadroid.aviasale

import android.app.Application
import com.alfadroid.di.AirTicketsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication: Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                AirTicketsModule.networkModule +
                        AirTicketsModule.airTicketsModule +
                        AirTicketsModule.departureModule
            )
        }
    }
}
