package com.technocraze.mvvmdatehistroy.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class SpyViewModelTest {

  private lateinit var data: MutableLiveData<String>

  @get:Rule
  val rule = InstantTaskExecutorRule()
  lateinit var viewModel: SpyViewModel
  lateinit var dataObserver: Observer<String>


  @Before
  fun setUp() {
    viewModel = SpyViewModel()
    data = spyk(viewModel.data)
    dataObserver = mockk()
    data.observeForever(dataObserver)
  }

  @Test
  fun testUpdateData() {

    every { data.postValue(any()) } just Runs

    viewModel.updateData("Updated Value")

    verify { data.postValue("Updated Value") }

  }

}