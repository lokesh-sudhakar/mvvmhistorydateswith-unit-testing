package com.technocraze.mvvmdatehistroy.repository

import com.technocraze.mvvmdatehistroy.model.Dates
import com.technocraze.mvvmdatehistroy.service.NetworkResult


interface HistoryRepository {
  suspend fun fetchHistory() : NetworkResult<List<Dates>>
}
