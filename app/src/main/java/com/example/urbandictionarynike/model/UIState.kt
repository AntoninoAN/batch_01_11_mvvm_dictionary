package com.example.urbandictionarynike.model

sealed class UIState{
    data class Response(val presentationData: List<DefinitionItem>): UIState()
    data class Error(val errorMessage: String): UIState()
    data class Loading(val isLoading: Boolean = true): UIState()
    data class Online(val isOnline: Boolean = true): UIState()
}
