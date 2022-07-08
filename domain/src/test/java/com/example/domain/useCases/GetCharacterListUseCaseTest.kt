package com.example.domain.useCases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.Constant
import com.example.domain.MainCoroutineRule
import com.example.domain.repository.MarvelRepo
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class GetCharacterListUseCaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var useCase: GetCharacterListUseCase

    @MockK
    private lateinit var repository: MarvelRepo

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        useCase = GetCharacterListUseCase(repository)
    }

    @Test
    fun testGetCharacterListsUseCaseCalledOrNot() {
        runTest {
           `when`(repository.getCharactersList(Constant.paginatedValue))
                .thenReturn(emptyList())

            useCase.invoke(Constant.paginatedValue)
            verify(repository.getCharactersList(Constant.paginatedValue))
        }
    }
}