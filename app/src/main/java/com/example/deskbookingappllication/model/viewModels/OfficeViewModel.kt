package com.example.deskbookingappllication.model.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.deskbookingappllication.api.RetrofitInstance
import com.example.deskbookingappllication.model.Office
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class OfficeViewModel (application: Application) : AndroidViewModel(
    application
) {
    val TAG = "OfficeViewModel"
    private var officeList = MutableLiveData<List<Office>>()
    val offices : LiveData<List<Office>> get() = officeList

    fun loadoffices(){
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.officeApi.getOfficeList()

            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                null
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                null
            }
            if (response?.body() != null && response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    officeList.value = response.body()!!
                }


            } else {
                Log.e(TAG, "Response not successful")
            }
        }
    }


}