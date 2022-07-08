package com.example.domain.useCases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.Constant
import com.example.domain.MainCoroutineRule
import com.example.domain.model.ModelCharacterDetail
import com.example.domain.repository.MarvelRepo
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class GetCharacterDetailUseCaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var useCase: GetCharacterDetailsUseCase

    @MockK
    private lateinit var repository: MarvelRepo

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        useCase = GetCharacterDetailsUseCase(repository)
    }

    @Test
    fun testCharacterDetailsUseCaseCalledOrNot() {

        val response = ModelCharacterDetail(Constant.ID_1,Constant.Iron_Man,Constant.Best_Avenger,Constant.Iron_Man_Image,Constant.IMG_JPG)

        runTest {
            Mockito.`when`(repository.getCharactersDetails(Constant.paginatedValue))
                .thenReturn(response)

            useCase.invoke(Constant.paginatedValue)
            Mockito.verify(repository.getCharactersDetails(Constant.paginatedValue))
        }
    }
}