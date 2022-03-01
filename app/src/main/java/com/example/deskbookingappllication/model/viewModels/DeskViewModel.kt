package com.example.deskbookingappllication.model.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.deskbookingappllication.api.RetrofitInstance
import com.example.deskbookingappllication.model.Desk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class DeskViewModel(application: Application) : AndroidViewModel(
    application
) {
    private val TAG = "DeskViewModel"
    private var deskList = MutableLiveData<List<Desk>>()
    val desks: LiveData<List<Desk>> get() = deskList

    fun loadDesks() {
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.deskApi.getListOfDesks()

            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                null
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                null
            }
            if (response?.body() != null && response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    deskList.value = response.body()!!
                }


            } else {
                Log.e(TAG, "Response not successful")
            }
        }
    }

    fun loadFavouriteDesks() {
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.deskApi.getListOfFavouriteDesks()

            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                null
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                null
            }
            if (response?.body() != null && response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    deskList.value = response.body()!!
                }


            } else {
                Log.e(TAG, "Response not successful")
            }
        }
    }
}