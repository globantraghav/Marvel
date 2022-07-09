package com.example.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.Constant
import com.example.data.MainCoroutineRule
import com.example.data.remote.NetworkApi
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MarvelRepoImplTests {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var repoImpl: MarvelRepoImpl

    @MockK
    private lateinit var networkApi: NetworkApi

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        repoImpl = MarvelRepoImpl(networkApi)
    }

    @Test
    fun testCharacterListCalled() {
        runTest {
            repoImpl.getCharactersList(Constant.paginatedValue)
            coVerify {
                networkApi.getCharactersList(offset = Constant.paginatedValue.toString())
            }
        }
    }

    @Test
    fun testCharacterDetailCalled() {
        runTest {
            repoImpl.getCharactersDetails(Constant.ID_1)
            coVerify {
                networkApi.getCharactersDetails(characterId = Constant.ID_1)
            }
        }
    }

}