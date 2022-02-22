package com.example.deskbookingappllication.model.room.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.deskbookingappllication.model.room.entities.Office
import com.example.deskbookingappllication.model.room.daos.OfficeDao


@Database(entities = [Office::class], version = 1)
abstract class OfficeDatabase : RoomDatabase() {
    abstract fun officeDao(): OfficeDao

    companion object {
        @Volatile
        private var INSTANCE: OfficeDatabase? = null

        fun getOfficeDatabase(context: Context): OfficeDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OfficeDatabase::class.java,
                    "office_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}