package com.example.urbandictionarynike.di.module

import com.example.urbandictionarynike.model.Repository
import com.example.urbandictionarynike.model.RepositoryImpl
import com.example.urbandictionarynike.model.local.CacheManager
import com.example.urbandictionarynike.model.remote.NetworkManager
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(cacheManager: CacheManager,
                          networkManager: NetworkManager): Repository{
        return RepositoryImpl(cacheManager, networkManager)
    }
}