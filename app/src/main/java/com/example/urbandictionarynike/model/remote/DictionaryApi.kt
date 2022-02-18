package com.example.urbandictionarynike.model.remote

import com.example.urbandictionarynike.model.DefinitionResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface DictionaryApi {
    @GET()
    //@Header("")
    suspend fun getDefinitions(): DefinitionResponse
}
//https://mashape-community-urban-dictionary.p.rapidapi.com
///define
// ?term=dude

//.addHeader("x-rapidapi-host", "mashape-community-urban-dictionary.p.rapidapi.com")
//.addHeader("x-rapidapi-key", "6b806dd214msh585207d010d812fp163207jsnbab67eb171e2")