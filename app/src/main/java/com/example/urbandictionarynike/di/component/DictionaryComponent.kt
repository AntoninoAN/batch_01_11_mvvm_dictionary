package com.example.urbandictionarynike.di.component

import com.example.urbandictionarynike.MainActivity
import com.example.urbandictionarynike.di.module.ContextModule
import com.example.urbandictionarynike.di.module.LocalModule
import com.example.urbandictionarynike.di.module.NetworkModule
import com.example.urbandictionarynike.di.module.RepositoryModule
import dagger.Component

@Component(
    modules = [LocalModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ContextModule::class]
)
interface DictionaryComponent {
    fun inject(activity: MainActivity)
}