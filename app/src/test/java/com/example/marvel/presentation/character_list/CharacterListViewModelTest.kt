package com.example.marvel.presentation.character_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvel.MainCoroutineRule
import com.example.marvel.common.Constants
import com.example.marvel.common.Resource
import com.example.marvel.domain.model.Character
import com.example.marvel.domain.use_cases.CharacterListUseCase
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
class CharacterListViewModelTest{

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: CharacterListViewModel

    @MockK
    private lateinit var useCase: CharacterListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel  = CharacterListViewModel(useCase)
    }

    @Test
    fun testCharactersForEmptyResponse() {

        runTest {
            val actualResponse = MarvelListData(
                isLoading = false,
                characterList = emptyList(),
                error = "")
            coEvery { useCase.invoke(Constants.Offset)} returns flowOf(Resource.Success(emptyList()))
            viewModel.getCharacters(Constants.Offset)
            Assert.assertEquals(viewModel.marvelList.getOrAwaitValue(),actualResponse)
        }
    }

    @Test
    fun testCharactersForNonEmptyResponse() {

        runTest{
            val actualResponse = MarvelListData(
                isLoading = false,
                characterList = listOf(Character(1,
                    Constants.Iron_Man,
                    Constants.Best_Avenger,
                    Constants.Iron_Man_Image,
                    Constants.IMG_JPG)),
                error = "")
               coEvery { useCase.invoke(Constants.Offset)} returns flowOf(Resource.Success(
                   listOf(Character(1,
                       Constants.Iron_Man,
                       Constants.Best_Avenger,
                       Constants.Iron_Man_Image,
                       Constants.IMG_JPG))))
               viewModel.getCharacters(Constants.Offset)
              Assert.assertEquals(viewModel.marvelList.getOrAwaitValue(),actualResponse)
        }
    }

    @Test
    fun testErrorNoCharacter() {

        runTest {
            val actualResponse = MarvelListData(
                isLoading = false,
                characterList = emptyList(),
                error = Constants.Non_Empty_List)
            coEvery { useCase.invoke(Constants.Offset)} returns flowOf(Resource.Error(Constants.Non_Empty_List))
            viewModel.getCharacters(Constants.Offset)
            Assert.assertEquals(viewModel.marvelList.getOrAwaitValue(),actualResponse)
        }
    }

    @Test
    fun testCharactersForNonEqualResponse() {

        runTest {
            val actualResponse = MarvelListData(
                isLoading = false,
                characterList = listOf(Character(
                    1,
                    Constants.Iron_Man,
                    Constants.Best_Avenger,
                    Constants.Iron_Man_Image,
                    Constants.IMG_JPG)),
                error = "")
            coEvery { useCase.invoke(Constants.Offset)} returns flowOf(Resource.Success(
                listOf(Character(2,
                    Constants.Captain,
                    Constants.First_Avenger,
                    Constants.Captain_America_Image,
                    Constants.IMG_JPG))))
            viewModel.getCharacters(Constants.Offset)
            Assert.assertNotEquals(viewModel.marvelList.getOrAwaitValue(),actualResponse)
        }
    }

}