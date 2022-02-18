package com.example.urbandictionarynike.model.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkManager {
    companion object{
        const val BASE_URL = "https://mashape-community-urban-dictionary.p.rapidapi.com"
        const val END_POINT = "define"
        const val PARAM_TERM = "term"
    }

    val dictionaryApi: DictionaryApi by lazy {
        initRetrofit().create(DictionaryApi::class.java)
    }

    private fun initRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(createClient())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)

        return OkHttpClient.Builder()
            .addInterceptor(
                object: Interceptor{
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val request = chain.request()
                            .newBuilder()
                            .addHeader("x-rapidapi-host",
                                "mashape-community-urban-dictionary.p.rapidapi.com")
                            .addHeader("x-rapidapi-key",
                                "6b806dd214msh585207d010d812fp163207jsnbab67eb171e2")
                            .build()
                        return chain.proceed(request)
                    }
                }
            )
            .addInterceptor(logging)
            .build()
    }
}