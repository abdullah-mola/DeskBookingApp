package com.example.deskbookingappllication.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.deskbookingappllication.model.api.LoginRequestBody
import com.example.deskbookingappllication.model.api.LoginResponse
import com.example.deskbookingappllication.model.api.RetrofitInstance
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
    val statusCode: LiveData<Int> get()=responseCode
    private var _userLogin = MutableLiveData<LoginResponse>()
    val userLogin: LiveData<LoginResponse> get() = _userLogin

    fun login(user: LoginRequestBody) {



        viewModelScope.launch(Dispatchers.IO) {
            val response = try{
                RetrofitInstance.userApi.userLogin(user)
            } catch (e: IOException){
                Log.e(TAG,"IOException, you might not have internet connection")
                null
            }catch(e: HttpException){
                Log.e(TAG, "HttpException, unexpected response")
                null
            }
            if(response?.body() != null && response.isSuccessful){
                withContext(Dispatchers.Main){
                    Log.d(TAG,"Request was success")
                    responseCode.value = response.code()
                    _userLogin.value = response.body()
                }
            }else {
                withContext(Dispatchers.Main) {
                    Log.e(TAG, "Request was not successful")
                    responseCode.value = response?.code()
                }
            }
            }
            


        }


    fun register(user: User){
        val apiRegister = RetrofitInstance.userApi
        viewModelScope.launch(Dispatchers.IO){
            apiRegister.userRegister(user)

        }
    }

}