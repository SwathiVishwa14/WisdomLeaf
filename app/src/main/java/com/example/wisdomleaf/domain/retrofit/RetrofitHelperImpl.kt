package com.example.wisdomleaf.domain.retrofit

import android.util.Log
import com.example.wisdomleaf.data.model.BookListNetworkResponseItem
import com.example.wisdomleaf.domain.WebService
import com.example.wisdomleaf.domain.datastate.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RetrofitHelperImpl constructor(private val apiInterface: WebService) :
    RetrofitHelper {
    override suspend fun getBooksList(): Flow<DataState<List<BookListNetworkResponseItem>>> = flow {
        try {
            emit(DataState.Loading(true))
            delay(2000)
            delay(2000)
            val response = apiInterface.getBookList()

            Log.e("AR_OTP::", "ERROR : " + response)
            if (response.isSuccessful) {
                Log.e("AR_OTP::", "API isSuccessful: ")
                emit(DataState.Success(response.body()!!))
                emit(DataState.Loading(false))
            } else {

                /*val apiError = errorUtils.parseError(response)!!
                if (apiError.message.isNotEmpty()) {
                    emit(DataState.Error(CancellationException(apiError.message[0])))
                } else {
                    emit(DataState.Error(e)
                }
*/
                //emit(DataState.Error(CancellationException("Something went wrong")))

                emit(DataState.Loading(false))

            }
        } catch (e: Exception) {
            Log.e("AR_OTP::", "Exception: $e.")
            emit(DataState.Error(e))
            emit(DataState.Loading(false))
        }
    }
}
