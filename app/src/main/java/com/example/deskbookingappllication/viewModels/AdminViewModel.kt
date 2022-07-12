package com.example.deskbookingappllication.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.deskbookingappllication.api.RetrofitInstance
import com.example.deskbookingappllication.model.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class AdminViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "AdminViewModel"
    private var commentList = MutableLiveData<List<Comment>>()
    val comment: LiveData<List<Comment>> get() = commentList

    fun loadComments(id:String) {
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.deskApi.getListOfComments(id)
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                null
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                null
            }
            if (response?.body() != null && response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    commentList.value = response.body()
                }
            } else {
                Log.e(TAG, "Response not successful")
            }
        }
    }
}