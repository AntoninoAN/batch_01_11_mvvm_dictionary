package com.example.urbandictionarynike.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.urbandictionarynike.model.Repository
import kotlin.coroutines.CoroutineContext

class DictionaryProvider(
    private val repository: Repository,
    private val coroutineContext: CoroutineContext
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DictionaryViewModel(repository, coroutineContext) as T
    }
}