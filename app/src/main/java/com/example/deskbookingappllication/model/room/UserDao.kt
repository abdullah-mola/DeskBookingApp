package com.example.deskbookingappllication.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.deskbookingappllication.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAllUsers(): LiveData<List<User>>

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    fun insertUser(user:User)
}