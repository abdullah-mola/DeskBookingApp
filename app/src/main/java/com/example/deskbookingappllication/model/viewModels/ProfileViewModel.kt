package com.example.deskbookingappllication.model.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.deskbookingappllication.api.RetrofitInstance
import com.example.deskbookingappllication.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class ProfileViewModel(application: Application) : AndroidViewModel(
    application
) {
    private val TAG = "ProfileViewModel"
    private var _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    fun loadUser(id: String) {
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.userApi.getUserById(id)

            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                null
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                null
            }
            if (response?.body() != null && response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    _user.value = response.body()!!
                }


            } else {
                Log.e(TAG, "Response not successful")
            }
        }
    }
}