package com.example.deskbookingappllication.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.deskbookingappllication.model.api.LoginRequestBody
import com.example.deskbookingappllication.model.api.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(
    application
) {


    fun login(user: LoginRequestBody) {
        val api = RetrofitInstance.userApi
        viewModelScope.launch(Dispatchers.IO) {
            api.userLogin(user)
        }
    }


}