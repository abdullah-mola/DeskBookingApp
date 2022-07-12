package com.example.deskbookingappllication.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.deskbookingappllication.api.RetrofitInstance
import com.example.deskbookingappllication.model.Desk
import com.example.deskbookingappllication.model.DeskId
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
    private var _desk = MutableLiveData<DeskId>()
    private var _deskStatusCode = MutableLiveData<Int>()
    val deskStatusCode: LiveData<Int> get() = _deskStatusCode

    private var _deskDetails = MutableLiveData<Desk>()
    val deskDetails: LiveData<Desk> get() = _deskDetails

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

    fun setDeskAsFavourite(desk: DeskId) {
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.deskApi.setDeskFavorite(desk)

            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                null
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                null
            }
            if (response?.isSuccessful!!) {
                withContext(Dispatchers.Main) {
                    _deskStatusCode.value = response.code()
                    Log.d(TAG, "setDeskAsFavourite: ${response.code()}")

                }
            } else {
                Log.e(TAG, "Response not successful")
            }
        }
    }

    fun removeDeskFromFavourite(desk: DeskId) {
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.deskApi.removeDeskFromFavorite(desk)

            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                null
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                null
            }
            if (response?.isSuccessful!!) {
                withContext(Dispatchers.Main) {
                    _deskStatusCode.value = response.code()
                    Log.d(TAG, "removeDeskFromFavourite: ${response.code()}")

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

    fun loadDesksByOfficeid(id: String) {
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.deskApi.getListOfDesksByOfficeId(id)

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

    fun getDeskDetails(id: String) {
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.deskApi.getDeskDetails(id)

            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                null
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                null
            }
            if (response?.body() != null && response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    _deskDetails.value = response.body()!!

                }


            } else {
                Log.e(TAG, "Response not successful")
            }
        }
    }
}