package com.example.urbandictionarynike.model

import kotlinx.coroutines.flow.Flow

interface Repository{
    fun getData(term: String): Flow<UIState>
}