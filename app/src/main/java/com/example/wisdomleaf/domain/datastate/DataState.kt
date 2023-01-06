package com.example.wisdomleaf.domain.datastate

sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error<out E>(val e: Exception) : DataState<E>()
    data class Loading<out B>(val b: Boolean) : DataState<B>()
}