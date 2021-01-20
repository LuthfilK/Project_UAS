package com.example.project_uas.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDB : RoomDatabase(){
    abstract fun noteDao() : NoteDao
    companion object {
        @Volatile private var instances : NoteDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instances ?: synchronized(LOCK){
            instances ?: buildDatabase(context).also{
                instances = it
            }
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDB::class.java,
            "note12345.db"
        ).build()
    }
}