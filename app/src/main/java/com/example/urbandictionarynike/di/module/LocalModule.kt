package com.example.urbandictionarynike.di.module

import android.content.Context
import com.example.urbandictionarynike.model.local.CacheManager
import com.example.urbandictionarynike.model.local.ConnectionManager
import com.example.urbandictionarynike.model.local.DictionaryDao
import com.example.urbandictionarynike.model.local.DictionaryRoom
import dagger.Module
import dagger.Provides

@Module
class LocalModule {
    @Provides
    fun provideCacheManager(
        dictionaryDao: DictionaryDao,
        context: Context
    ): CacheManager {
        return CacheManager(dictionaryDao, context)
    }

    @Provides
    fun provideConnectionManager(context: Context): ConnectionManager{
        return ConnectionManager(context)
    }

    @Provides
    fun provideDictionaryDao(context: Context): DictionaryDao{
        return DictionaryRoom.getDatabase(context).getDao()
    }
}