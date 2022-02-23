package com.example.deskbookingappllication.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.deskbookingappllication.model.api.LoginRequestBody
import com.example.deskbookingappllication.model.api.RetrofitInstance
import com.example.deskbookingappllication.model.room.databases.UserDatabase
import com.example.deskbookingappllication.model.room.UserRepository
import com.example.deskbookingappllication.model.room.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(
    application
) {
    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository
    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }
    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
    fun login(user:LoginRequestBody){
        val api = RetrofitInstance.userApi
        viewModelScope.launch(Dispatchers.IO){
            api.userLogin(user)
        }
    }


}