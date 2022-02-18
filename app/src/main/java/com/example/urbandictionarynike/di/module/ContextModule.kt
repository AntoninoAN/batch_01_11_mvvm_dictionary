package com.example.urbandictionarynike.di.module

import android.content.Context
import android.content.ContextWrapper
import android.view.ContextThemeWrapper
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context: Context) {
    @Provides
    fun provideContext(): Context{
        return context
    }
}