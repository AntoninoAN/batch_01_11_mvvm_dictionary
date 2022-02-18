package com.example.urbandictionarynike.di.module

import com.example.urbandictionarynike.model.remote.NetworkManager
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideNetworkManager(): NetworkManager{
        return NetworkManager()
    }
}