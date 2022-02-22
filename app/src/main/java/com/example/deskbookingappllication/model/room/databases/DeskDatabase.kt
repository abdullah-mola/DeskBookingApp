package com.example.deskbookingappllication.model.room.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.deskbookingappllication.model.room.daos.DeskDao
import com.example.deskbookingappllication.model.room.entities.Office

@Database(entities = [Office::class], version = 1)
abstract class DeskDatabase : RoomDatabase() {
    abstract fun deskDao(): DeskDao

    companion object {
        @Volatile
        private var INSTANCE: DeskDatabase? = null

        fun getDeskDatabase(context: Context): DeskDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DeskDatabase::class.java,
                    "desk_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}