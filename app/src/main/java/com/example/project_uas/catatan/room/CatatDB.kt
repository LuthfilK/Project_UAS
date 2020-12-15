package com.example.project_uas.catatan.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Catat::class],
    version = 1
)
abstract class CatatDB : RoomDatabase(){

    abstract fun catatDao() : CatatDao

    companion object {

        @Volatile private var instance : CatatDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CatatDB::class.java,
            "gw.db"
        ).build()

    }
}