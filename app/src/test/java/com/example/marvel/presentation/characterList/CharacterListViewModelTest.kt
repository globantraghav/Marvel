package com.example.marvel.presentation.characterList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.Constant
import com.example.common.Resource
import com.example.domain.model.ModelCharacter
import com.example.domain.useCases.GetCharacterListUseCase
import com.example.marvel.MainCoroutineRule
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
    fun `testCharactersListForEmptyResponse`() =

        runTest {
            val actualResponse = MarvelListData(
                isLoading = false,
                modelCharacterList = emptyList(),
                error = Constant.Empty_String
            )
            coEvery { useCase.invoke(Constant.paginatedValue) } returns flowOf(
                Resource.Success(
                    emptyList()
                )
            )
            viewModel.getCharactersList(Constant.paginatedValue)
            Assert.assertEquals(viewModel.marvelList.getOrAwaitValue(), actualResponse)
        }

    @Test
    fun `testCharactersListForNonEmptyResponse`() =

        runTest {
            val actualResponse = MarvelListData(
                isLoading = false,
                modelCharacterList = listOf(
                    ModelCharacter(
                        Constant.ID_1,
                        Constant.Iron_Man,
                        Constant.Best_Avenger,
                        Constant.Iron_Man_Image,
                        Constant.IMG_JPG
                    )
                ),
                error = Constant.Empty_String
            )
            coEvery { useCase.invoke(Constant.paginatedValue) } returns flowOf(
                Resource.Success(
                    listOf(
                        ModelCharacter(
                            Constant.ID_1,
                            Constant.Iron_Man,
                            Constant.Best_Avenger,
                            Constant.Iron_Man_Image,
                            Constant.IMG_JPG
                        )
                    )
                )
            )
            viewModel.getCharactersList(Constant.paginatedValue)
            Assert.assertEquals(viewModel.marvelList.getOrAwaitValue(), actualResponse)
        }

    @Test
    fun `testErrorNoCharacterList`() =

        runTest {
            val actualResponse = MarvelListData(
                isLoading = false,
                modelCharacterList = emptyList(),
                error = Non_Empty_List
            )
            coEvery { useCase.invoke(Constant.paginatedValue) } returns flowOf(
                Resource.Error(
                    Non_Empty_List
                )
            )
            viewModel.getCharactersList(Constant.paginatedValue)
            Assert.assertEquals(viewModel.marvelList.getOrAwaitValue(), actualResponse)
        }

    @Test
    fun `testCharactersListForNonEqualResponse`() =

        runTest {
            val actualResponse = MarvelListData(
                isLoading = false,
                modelCharacterList = listOf(
                    ModelCharacter(
                        Constant.ID_1,
                        Constant.Iron_Man,
                        Constant.Best_Avenger,
                        Constant.Iron_Man_Image,
                        Constant.IMG_JPG
                    )
                ),
                error = Constant.Empty_String
            )
            coEvery { useCase.invoke(Constant.paginatedValue) } returns flowOf(
                Resource.Success(
                    listOf(
                        ModelCharacter(
                            ID_2,
                            Captain,
                            First_Avenger,
                            Captain_America_Image,
                            Constant.IMG_JPG
                        )
                    )
                )
            )
            viewModel.getCharactersList(Constant.paginatedValue)
            Assert.assertNotEquals(viewModel.marvelList.getOrAwaitValue(), actualResponse)
        }

    companion object {
        const val Non_Empty_List = "Character list cannot be empty"
        const val Captain = "Captain America"
        const val First_Avenger = "First Avenger"
        const val Captain_America_Image = "captain_america.jpg"
        const val ID_2 = 2
    }
}