package com.example.deskbookingappllication.model.room.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.deskbookingappllication.model.room.entities.User
import com.example.deskbookingappllication.model.room.daos.UserDao

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                UserDatabase::class.java,
                "user_table").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}