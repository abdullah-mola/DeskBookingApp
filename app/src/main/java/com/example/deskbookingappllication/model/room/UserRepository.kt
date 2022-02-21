package com.example.deskbookingappllication.model.room

import androidx.lifecycle.LiveData
import com.example.deskbookingappllication.model.User

class UserRepository(private val userDao: UserDao){

    val readAllData: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun addUser(user:User){
        userDao.insertUser(user)
    }

}