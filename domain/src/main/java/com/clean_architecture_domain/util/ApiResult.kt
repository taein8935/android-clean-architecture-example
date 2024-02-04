package com.clean_architecture_domain.util

import com.clean_architecture_domain.util.ApiResult.Success
import com.clean_architecture_domain.util.ApiResult.Error


sealed class ApiResult<T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error<T>(val error: Throwable) : ApiResult<T>()
}

inline fun <T, R> ApiResult<T>.getResult(
    success: (Success<T>) -> R,
    error: (Error<T>) -> R
): R = when (this) {
    is Success -> success(this)
    is Error -> error(this)
}

inline fun <T> ApiResult<T>.onSuccess(
    action: (T) -> Unit
): ApiResult<T> {
    if (this is Success) action(data)
    return this
}

inline fun <T> ApiResult<T>.onError(
    action: (Throwable) -> Unit
): ApiResult<T> {
    if (this is Error) action(error)
    return this
}
