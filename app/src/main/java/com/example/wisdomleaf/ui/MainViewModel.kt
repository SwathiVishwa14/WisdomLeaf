package com.example.wisdomleaf.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wisdomleaf.domain.datastate.DataState
import com.example.wisdomleaf.domain.model.BookListResponse
import com.example.wisdomleaf.domain.model.BookListResponseItem
import com.example.wisdomleaf.domain.repo.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor
    (private val repository: AppRepository) : ViewModel() {
    private val taskEventChannel = Channel<TaskEvent>()
    val taskEvent = taskEventChannel.receiveAsFlow()

    init {
        getBooksList()
    }

    private val _booksList: MutableLiveData<DataState<List<BookListResponseItem>>> =
        MutableLiveData()
    val booksListData: LiveData<DataState<List<BookListResponseItem>>>
        get() = _booksList


    fun getBooksList() = viewModelScope.launch(Dispatchers.IO) {
        Log.d("logMsg", "viewModel Function")

        repository.getCoinList()
            .onEach {
                _booksList.value = it
            }
            .launchIn(viewModelScope)
    }


}

sealed class TaskEvent {
}
