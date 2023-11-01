package com.technocraze.mvvmdatehistroy.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.technocraze.mvvmdatehistroy.model.Dates
import com.technocraze.mvvmdatehistroy.service.ApiService
import com.technocraze.mvvmdatehistroy.service.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.internal.util.MockUtil

class HistoryRepositoryImplTest{

  val testDispatcher =  StandardTestDispatcher()
  @get:Rule
  val executor = InstantTaskExecutorRule()

  @Mock
  lateinit var apiService: ApiService


  @Before
  fun setUp(){
    MockitoAnnotations.openMocks(this)
    Dispatchers.setMain(testDispatcher)
  }

  @Test
  fun `test fetch histoty response when empty received from server expect Network Error res`(){
    runTest {
      Mockito.`when`(apiService.getHistoryOfToday()).thenReturn(arrayListOf())
      val sut = HistoryRepositoryImpl(apiService);
      var res = sut.fetchHistory()
      assertEquals(NetworkResult.Error<List<Dates>>("Something went wrong").message, res.message)
    }
  }

  @Test
  fun `test fetch histoty response when null received from server expect Network Error res`(){
    runTest {
      Mockito.`when`(apiService.getHistoryOfToday()).thenReturn(null)
      val sut = HistoryRepositoryImpl(apiService);
      var res = sut.fetchHistory()
      assertEquals(NetworkResult.Error<List<Dates>>("Something went wrong").message, res.message)
    }
  }

  @Test
  fun `test fetch histoty when list received from server expect Network Success res`(){
    runTest {
      val dataResponse = arrayListOf<Dates>(
        Dates("1-November-1973", "This is dummy data")
      )
      Mockito.`when`(apiService.getHistoryOfToday()).thenReturn(dataResponse)
      val sut = HistoryRepositoryImpl(apiService);
      var res = sut.fetchHistory()
      var expected = NetworkResult.Success<List<Dates>>("Success", dataResponse)
      assertEquals(expected.message, res.message)
      assertEquals(expected.data?.size, 1)

    }
  }

  @After
  fun cleanUp(){
    Dispatchers.resetMain()
  }

}