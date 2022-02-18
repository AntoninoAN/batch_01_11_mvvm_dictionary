package com.example.urbandictionarynike.di.module

import com.example.urbandictionarynike.model.Repository
import com.example.urbandictionarynike.viewmodel.DictionaryProvider
import dagger.Module
import dagger.Provides
import kotlin.coroutines.CoroutineContext

@Module
class DictionaryProviderModule {
    @Provides
    fun provideDictionaryProvider(
        repository: Repository,
        coroutineContext: CoroutineContext
    ): DictionaryProvider {
        return DictionaryProvider(repository, coroutineContext)
    }
}