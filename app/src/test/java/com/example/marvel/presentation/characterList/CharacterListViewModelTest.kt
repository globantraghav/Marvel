package com.example.marvel.presentation.characterList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvel.MainCoroutineRule
import com.example.marvel.common.Constants
import com.example.marvel.common.Resource
import com.example.marvel.domain.model.ModelCharacter
import com.example.marvel.domain.useCases.GetCharacterListUseCase
import com.example.marvel.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: CharacterListViewModel

    @MockK
    private lateinit var useCase: GetCharacterListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = CharacterListViewModel(useCase)
    }

    @Test
    fun testCharactersForEmptyResponse() {

        runTest {
            val actualResponse = MarvelListData(
                isLoading = false,
                modelCharacterList = emptyList(),
                error = Constants.Empty_String
            )
            coEvery { useCase.invoke(Constants.paginatedValue) } returns flowOf(
                Resource.Success(
                    emptyList()
                )
            )
            viewModel.getCharactersList(Constants.paginatedValue)
            Assert.assertEquals(viewModel.marvelList.getOrAwaitValue(), actualResponse)
        }
    }

    @Test
    fun testCharactersForNonEmptyResponse() {

        runTest {
            val actualResponse = MarvelListData(
                isLoading = false,
                modelCharacterList = listOf(
                    ModelCharacter(
                        Constants.ID_1,
                        Constants.Iron_Man,
                        Constants.Best_Avenger,
                        Constants.Iron_Man_Image,
                        Constants.IMG_JPG
                    )
                ),
                error = Constants.Empty_String
            )
            coEvery { useCase.invoke(Constants.paginatedValue) } returns flowOf(
                Resource.Success(
                    listOf(
                        ModelCharacter(
                            Constants.ID_1,
                            Constants.Iron_Man,
                            Constants.Best_Avenger,
                            Constants.Iron_Man_Image,
                            Constants.IMG_JPG
                        )
                    )
                )
            )
            viewModel.getCharactersList(Constants.paginatedValue)
            Assert.assertEquals(viewModel.marvelList.getOrAwaitValue(), actualResponse)
        }
    }

    @Test
    fun testErrorNoCharacter() {

        runTest {
            val actualResponse = MarvelListData(
                isLoading = false,
                modelCharacterList = emptyList(),
                error = Constants.Non_Empty_List
            )
            coEvery { useCase.invoke(Constants.paginatedValue) } returns flowOf(
                Resource.Error(
                    Constants.Non_Empty_List
                )
            )
            viewModel.getCharactersList(Constants.paginatedValue)
            Assert.assertEquals(viewModel.marvelList.getOrAwaitValue(), actualResponse)
        }
    }

    @Test
    fun testCharactersForNonEqualResponse() {

        runTest {
            val actualResponse = MarvelListData(
                isLoading = false,
                modelCharacterList = listOf(
                    ModelCharacter(
                        Constants.ID_1,
                        Constants.Iron_Man,
                        Constants.Best_Avenger,
                        Constants.Iron_Man_Image,
                        Constants.IMG_JPG
                    )
                ),
                error = Constants.Empty_String
            )
            coEvery { useCase.invoke(Constants.paginatedValue) } returns flowOf(
                Resource.Success(
                    listOf(
                        ModelCharacter(
                            Constants.ID_2,
                            Constants.Captain,
                            Constants.First_Avenger,
                            Constants.Captain_America_Image,
                            Constants.IMG_JPG
                        )
                    )
                )
            )
            viewModel.getCharactersList(Constants.paginatedValue)
            Assert.assertNotEquals(viewModel.marvelList.getOrAwaitValue(), actualResponse)
        }
    }

}