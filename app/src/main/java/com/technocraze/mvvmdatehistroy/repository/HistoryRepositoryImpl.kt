package com.technocraze.mvvmdatehistroy.repository

import com.technocraze.mvvmdatehistroy.model.Dates
import com.technocraze.mvvmdatehistroy.service.ApiService
import com.technocraze.mvvmdatehistroy.service.NetworkResult
import javax.inject.Inject


class HistoryRepositoryImpl @Inject
constructor(private val apiService: ApiService) : HistoryRepository {

  override suspend fun fetchHistory():
      NetworkResult<List<Dates>> {
    var res = apiService.getHistoryOfToday()
    return  if (res.isNullOrEmpty()) {
       NetworkResult.Error("Something went wrong")
    } else {
       NetworkResult.Success("Success",res)
    }
  }
}