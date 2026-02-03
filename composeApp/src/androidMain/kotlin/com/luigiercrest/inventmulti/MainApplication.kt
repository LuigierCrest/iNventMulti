package com.luigiercrest.inventmulti

import android.app.Application
import com.luigiercrest.inventmulti.di.appModule
import com.luigiercrest.presentation.platformAndroidModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate (){
        super.onCreate()
        if (GlobalContext.getOrNull() == null){
            startKoin{
                androidContext(this@MainApplication)
                modules(appModule, platformAndroidModule)
            }
        }
    }
}