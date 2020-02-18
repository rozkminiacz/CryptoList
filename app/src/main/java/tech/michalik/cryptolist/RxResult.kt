package tech.michalik.cryptolist

sealed class RxResult<T> {
    data class Success<T>(val value: T) : RxResult<T>()
    data class Error<T>(val throwable: Throwable) : RxResult<T>()
}