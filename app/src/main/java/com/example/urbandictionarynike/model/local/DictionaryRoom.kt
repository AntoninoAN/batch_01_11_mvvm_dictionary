package com.example.urbandictionarynike.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [], version = 1)
abstract class DictionaryRoom : RoomDatabase() {

    abstract fun getDao(): DictionaryDao

    companion object{
        @Volatile
        private var INSTANCE: DictionaryRoom? = null

        fun getDatabase(context: Context): DictionaryRoom{
            val temp = INSTANCE
            if (temp != null) return temp

            synchronized(this){
                var instance = INSTANCE
                if (instance != null) return instance

                instance = Room.databaseBuilder(
                    context,
                    DictionaryRoom::class.java,
                    "dictionary")
                    //.allowMainThreadQueries() //DONT use this in Production.
                    .build()

                INSTANCE = instance

                return instance
            }
        }
    }
}