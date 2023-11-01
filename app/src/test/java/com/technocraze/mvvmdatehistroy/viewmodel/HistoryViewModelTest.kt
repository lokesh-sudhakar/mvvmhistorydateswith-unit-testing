package com.technocraze.mvvmdatehistroy.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.technocraze.mvvmdatehistroy.model.Dates
import com.technocraze.mvvmdatehistroy.repository.HistoryRepository
import com.technocraze.mvvmdatehistroy.service.NetworkResult
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.stubbing.Answer


class HistoryViewModelTest {

  private val testDispatcher = StandardTestDispatcher()
  // private val testDispatcher = TestCoroutineScheduler()


  @get:Rule
  val instantTaskExecutorRule = InstantTaskExecutorRule()

  @Mock
  lateinit var repository: HistoryRepository

  @Before
  fun setup() {
    MockitoAnnotations.openMocks(this)
    Dispatchers.setMain(testDispatcher)
  }

  @Test
  fun `get history data from api`() {
    runTest {
      val dataResponse = arrayListOf<Dates>(
        Dates("1-November-1973", "This is dummy data")
      )
      Mockito.`when`(repository.fetchHistory())
        .thenReturn(NetworkResult.Success<List<Dates>>("success",dataResponse))
      val viewModel = HistoryViewModel(repository)
      viewModel.getHistory()
      testDispatcher.scheduler.advanceUntilIdle()
      viewModel.historyState.test {
        var item = awaitItem()
        assertEquals(1, item.historyDates.size)
        cancel()
      }
    }
  }

  @Test
  fun `get history data that is null  expecting error`() {
    runTest {
      Mockito.`when`(repository.fetchHistory()).thenReturn(NetworkResult.Error("Something went wrong"))
      val viewModel = HistoryViewModel(repository)
      viewModel.getHistory()
      testDispatcher.scheduler.advanceUntilIdle()
      viewModel.historyState.test {
        var item = awaitItem()
        var expected = HistoryViewModel.HistoryState(
          isLoading = false,
          historyDates = emptyList(),
          errorMessage = "Something went wrong"
        )
        assertEquals(expected, item)
        cancel()
      }
    }
  }

  @Test
  fun `default state of loading when viewmodel initialized`() {
    runTest {
      val viewModel = HistoryViewModel(repository)
      viewModel.historyState.test {
        var state = awaitItem()
        assertEquals(true, state.isLoading)
        cancel()
      }
    }
  }

  @Test
  fun `default state of loading after data loaded`() {
    runTest {
      val dataResponse = arrayListOf<Dates>(
        Dates("1-November-1973", "This is dummy data")
      )
      Mockito.`when`(repository.fetchHistory())
        .thenReturn(NetworkResult.Success<List<Dates>>("success",dataResponse))
      val viewModel = HistoryViewModel(repository)
      viewModel.getHistory()
      testDispatcher.scheduler.advanceUntilIdle()
      viewModel.historyState.test {
        val state = awaitItem()
        var expected = HistoryViewModel.HistoryState(
          isLoading = false,
        )
        assertEquals(expected.isLoading, state.isLoading)
        cancel()
      }
    }
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }


}