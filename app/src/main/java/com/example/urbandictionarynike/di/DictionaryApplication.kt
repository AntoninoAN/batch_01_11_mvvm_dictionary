package com.example.urbandictionarynike.di

import android.app.Application
import com.example.urbandictionarynike.di.component.DictionaryComponent

class DictionaryApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // DaggerDictionaryComponent
        //component = DaggerDictionaryComponent
    }

    companion object{
        lateinit var component: DictionaryComponent
    }
}