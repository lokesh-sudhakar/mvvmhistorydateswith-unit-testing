package com.technocraze.mvvmdatehistroy.di

import com.technocraze.mvvmdatehistroy.repository.HistoryRepository
import com.technocraze.mvvmdatehistroy.repository.HistoryRepositoryImpl
import com.technocraze.mvvmdatehistroy.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

  @Singleton
  @Provides
  fun getOkhttpClient(): OkHttpClient {
    return OkHttpClient.Builder().addInterceptor { chain ->
      val request: Request = chain.request().newBuilder()
        .addHeader("X-RapidAPI-Key", "de2e0be4e3msh550ca3ddc0e3cebp155babjsnfe507ee4ddaa")
        .addHeader("X-RapidAPI-Host", "current-affairs-of-india.p.rapidapi.com").build()
      chain.proceed(request)
    }.connectTimeout(30, TimeUnit.SECONDS)
      .readTimeout(30, TimeUnit.SECONDS)
      .build()
  }

  @Singleton
  @Provides
  fun getRetrofit(okhttp : OkHttpClient): Retrofit {

    val retrofit = Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl("https://current-affairs-of-india.p.rapidapi.com/")
      .client(okhttp)
      .build()
    return retrofit
  }

  @Singleton
  @Provides
  fun getAPiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
  }

  @Provides
  fun getRepository(apiService: ApiService): HistoryRepository {
    return HistoryRepositoryImpl(apiService)
  }
}