package com.technocraze.mvvmdatehistroy.service

import com.technocraze.mvvmdatehistroy.model.Dates
import retrofit2.http.GET
import java.util.Date

interface ApiService {

  @GET("/history-of-today")
  suspend fun getHistoryOfToday(): List<Dates>?

}