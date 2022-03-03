package com.example.deskbookingappllication.model.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.deskbookingappllication.api.RetrofitInstance
import com.example.deskbookingappllication.model.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class BookingViewModel(application: Application) : AndroidViewModel(
    application
) {
    val TAG = "BookingViewModel"
    private var _book = MutableLiveData<Book>()
    val book: LiveData<Book> get() = _book

    fun booking(booking: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.deskApi.bookADesk(booking)
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                null
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                null
            }
            if (response?.body() != null && response.isSuccessful) {
                withContext(Dispatchers.Main) {

                    _book.value = response.body()

                }

            } else {
                Log.e(TAG, "Response not successful")
            }
        }
    }
}