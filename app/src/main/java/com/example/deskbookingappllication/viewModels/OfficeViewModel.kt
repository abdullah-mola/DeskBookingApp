package com.example.deskbookingappllication.viewModels

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

class OfficeViewModel(application: Application) : AndroidViewModel(
    application
) {
    private val TAG = "OfficeViewModel"

    //    private var _office = MutableLiveData<String>()
//    val office :LiveData<String> get() = _office
    private var _officeList = MutableLiveData<List<Office>>()
    val officeList: LiveData<List<Office>> get() = _officeList

    fun loadOffices() {
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
                    _officeList.value = response.body()!!

                }
            } else {
                Log.e(TAG, "Response not successful")
            }
        }
    }


}