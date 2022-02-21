package com.example.urbandictionarynike.di

import android.app.Application
import com.example.urbandictionarynike.di.component.DaggerDictionaryComponent
import com.example.urbandictionarynike.di.component.DictionaryComponent
import com.example.urbandictionarynike.di.module.*

class DictionaryApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // DaggerDictionaryComponent
        component = DaggerDictionaryComponent.builder()
            .contextModule(ContextModule(applicationContext))
            .dictionaryProviderModule(DictionaryProviderModule())
            .localModule(LocalModule())
            .networkModule(NetworkModule())
            .repositoryModule(RepositoryModule())
            .build()
    }

    companion object{
        lateinit var component: DictionaryComponent
    }
}