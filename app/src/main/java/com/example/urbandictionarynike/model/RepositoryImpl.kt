package com.example.urbandictionarynike.model

import com.example.urbandictionarynike.model.local.CacheManager
import com.example.urbandictionarynike.model.local.ConnectionManager
import com.example.urbandictionarynike.model.remote.NetworkManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImpl(
    private val cacheManager: CacheManager,
    private val networkManager: NetworkManager
) : Repository {

    override fun getData(term: String): Flow<UIState> {
        // check offline/online scenario.
        // check stale cache(DB) for response
        // if data and not stale, return else show error
        // if data and stale, show error
        // if Online
        // check cache
        // if data, return else
        // do network and update cache
        // else error from network.
        return flow { // this is a flow Builder
            emit(UIState.Loading())
            if (ConnectionManager.isConnected) {
                emit(UIState.Online())
                if (cacheManager.isStale()) {
                    emit(UIState.Error("Not updated definitions."))
                } else {
                    if (cacheManager.contains(term)) {
                        emit(UIState.Loading(false))
                        emit(UIState.Response(cacheManager.getDefinitions(term)))
                    } else {
                        cacheManager.updateNewNetworkRequest()
                        val response = networkManager.dictionaryApi.getDefinitions()
                        cacheManager.updateCache(response)
                        emit(
                            UIState.Response(cacheManager.getDefinitions(term))
                        )
                    }
                }
            }else{
                emit(UIState.Online(false))
                if (cacheManager.isStale()) {
                    emit(UIState.Error("Not updated definitions."))
                }else{
                    if (cacheManager.contains(term)) {
                        emit(UIState.Loading(false))
                        emit(UIState.Response(cacheManager.getDefinitions(term)))
                    }else{
                        emit(UIState.Error("No previous definitions."))
                    }
                }
            }
        }
    }
}
