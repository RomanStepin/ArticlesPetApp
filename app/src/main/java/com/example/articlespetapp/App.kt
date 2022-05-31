package com.example.articlespetapp

import android.app.Application
import com.example.articlespetapp.di.DaggerNotificationsComponent
import com.example.articlespetapp.di.NotificationsComponent


class App: Application() {

    companion object {
        private lateinit var appInstance: App
        val notificationsComponent: NotificationsComponent = DaggerNotificationsComponent.create()

        fun getInstance(): App {
            return appInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }
}

