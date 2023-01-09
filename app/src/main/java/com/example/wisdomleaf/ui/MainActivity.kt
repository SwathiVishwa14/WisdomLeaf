package com.example.wisdomleaf.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wisdomleaf.databinding.ActivityMainBinding
import com.example.wisdomleaf.domain.datastate.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val bookListViewModel: MainViewModel by viewModels()
    private var logMsg: String = "Doodlelue"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pullToRefresh.apply {
            setOnRefreshListener {
                bookListViewModel.getBooksList()
                isRefreshing = false
            }
        }

        bookListViewModel.getBooksList()
        Log.d("Doodlelue", "Initial")
        subscribeObserver()
    }

    private fun subscribeObserver() {
        bookListViewModel.booksListData.observe(this) { dataState ->
            when (dataState) {
                is DataState.Error -> {
                    Log.d(logMsg, "Observe")
                    Log.e("GetCoin List::", "Error : ${dataState.e}")
//                    context?.toast("error")
                }
                is DataState.Loading -> {
//                    Log.e("SELL_OFFER_LIST::", "UI List size: ${dataState.loading}")
                }
                is DataState.Success -> {
                    Log.e("SELL_OFFER_LIST::", "UI List size: ${dataState.data}")
                    Log.d(logMsg, "Event")
                    binding.bookListRv.layoutManager = LinearLayoutManager(this)
                    binding.bookListRv.adapter = BookListAdapter(
                        dataState.data,
                        this,
                    )
                }
            }
        }

    }

    fun <T> Activity.collectLatestFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collectLatest(collect)
            }
        }
    }
}
