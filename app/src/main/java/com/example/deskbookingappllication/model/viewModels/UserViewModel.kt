package com.example.deskbookingappllication.model.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.deskbookingappllication.api.LoginRequestBody
import com.example.deskbookingappllication.api.LoginResponse
import com.example.deskbookingappllication.api.RetrofitInstance
import com.example.deskbookingappllication.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class UserViewModel(application: Application) : AndroidViewModel(
    application
) {
    private val TAG = "UserViewModel"
    private var responseCode = MutableLiveData<Int>()
    val statusCode: LiveData<Int> get() = responseCode
    private var registerResponseCode = MutableLiveData<Int>()
    val registerstatusCode: LiveData<Int> get() = registerResponseCode
    private var _userLogin = MutableLiveData<LoginResponse>()
    private var _userRegister = MutableLiveData<User>()
    val userRegister :LiveData<User> get() = _userRegister
    val userLoginData: LiveData<LoginResponse> get() = _userLogin
    fun login(user: LoginRequestBody) {


        viewModelScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.userApi.userLogin(user)
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                null
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                null
            }
            if (response?.body() != null && response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "Request was success")
                    responseCode.value = response.code()
                    _userLogin.value = response.body()
                }
            } else {
                withContext(Dispatchers.Main) {
                    Log.e(TAG, "Request was not successful")
                    responseCode.value = response?.code()
                }

            }

        }


    }


    fun register(user: User) {


        viewModelScope.launch(Dispatchers.IO) {
            val registerResponse = try {
                RetrofitInstance.userApi.userRegister(user)
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                null
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                null
            }
            if (registerResponse?.body() != null && registerResponse.isSuccessful) {
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "Request was success")
                    registerResponseCode.value = registerResponse.code()
                    _userRegister.value = registerResponse.body()
                }
            } else {
                withContext(Dispatchers.Main) {
                    Log.e(TAG, "Request was not successful")
                    registerResponseCode.value = registerResponse?.code()
                }
            }
        }
    }

}