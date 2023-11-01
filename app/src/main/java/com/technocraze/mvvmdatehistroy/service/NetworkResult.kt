package com.technocraze.mvvmdatehistroy.service

import com.technocraze.mvvmdatehistroy.model.Dates

sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {

  class Success<T>(message: String?,data: T) : NetworkResult<T>(data,message)
  class Error<T>(message: String?) : NetworkResult<T>(null,message)

}