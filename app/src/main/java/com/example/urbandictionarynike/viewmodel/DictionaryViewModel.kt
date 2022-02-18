package com.example.urbandictionarynike.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.urbandictionarynike.model.Repository
import com.example.urbandictionarynike.model.RepositoryImpl
import com.example.urbandictionarynike.model.UIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Constructor Injection.
 *
 */
class DictionaryViewModel(private val repo: Repository,
                          private val coroutineContext: CoroutineContext): ViewModel() {

    // Hard dependency, tight coupling.
    // val repository = RepositoryImpl()

    private val job = CoroutineScope(coroutineContext)
    private val _definitions = MutableLiveData<UIState>()
    val definitions: LiveData<UIState>
    get() = _definitions

    fun findNewTerm(newTerm: String){
        job.launch {
            repo.getData(newTerm).collect {
                _definitions.postValue(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel("ViewModel onCleared")
    }

}
