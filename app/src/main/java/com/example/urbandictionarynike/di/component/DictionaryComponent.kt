package com.example.urbandictionarynike.di.component

import com.example.urbandictionarynike.MainActivity
import com.example.urbandictionarynike.di.module.*
import dagger.Component

@Component(
    modules = [LocalModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ContextModule::class,
        DictionaryProviderModule::class]
)
interface DictionaryComponent {
    fun inject(activity: MainActivity)
}