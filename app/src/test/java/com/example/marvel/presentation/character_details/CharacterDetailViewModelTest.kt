package com.example.marvel.presentation.character_details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvel.MainCoroutineRule
import com.example.marvel.common.Constants
import com.example.marvel.common.Resource
import com.example.marvel.domain.model.CharacterDetail
import com.example.marvel.domain.use_cases.CharacterDetailsUseCase
import com.example.marvel.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.*

@ExperimentalCoroutinesApi
class CharacterDetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: CharacterDetailViewModel

    @MockK
    private lateinit var useCase: CharacterDetailsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel  = CharacterDetailViewModel(useCase)
    }

    @Test
    fun testCharactersDetailsForEqualResponse() {
        val characterDetailMock  =CharacterDetail(1,Constants.Iron_Man,Constants.Best_Avenger,Constants.Iron_Man_Image,Constants.IMG_JPG)

        runTest {
            val actualResponse = CharacterDetailData(
                isLoading = false,
                characterDetails = characterDetailMock,
                error = "")
            coEvery { useCase.invoke(1)} returns flowOf(Resource.Success(characterDetailMock))
            viewModel.getCharacterDetails(1)
            Assert.assertEquals(viewModel.characterDetails.getOrAwaitValue(),actualResponse)
        }
    }

    @Test
    fun testCharactersDetailsForNonEqualResponse() {
        val characterDetailMock  =CharacterDetail(1,Constants.Iron_Man,Constants.Best_Avenger,Constants.Iron_Man_Image,Constants.IMG_JPG)
        val characterDetailActual  =CharacterDetail(2,Constants.Captain,Constants.First_Avenger,Constants.Captain_America_Image,Constants.IMG_JPG)

        runTest{
            val actualResponse = CharacterDetailData(
                isLoading = false,
                characterDetails = characterDetailActual,
                error = "")
            coEvery { useCase.invoke(1)} returns flowOf(Resource.Success(characterDetailMock))
            viewModel.getCharacterDetails(1)
            Assert.assertNotEquals(viewModel.characterDetails.getOrAwaitValue(),actualResponse)
        }
    }

    @Test
    fun testCharactersDetailsForErrorResponse() {

        runTest {
            val actualResponse = CharacterDetailData(
                isLoading = false,
                characterDetails = null,
                error = Constants.Non_Empty_Character_Details)
            coEvery { useCase.invoke(1)} returns flowOf(Resource.Error(Constants.Non_Empty_Character_Details))
            viewModel.getCharacterDetails(1)
            Assert.assertEquals(viewModel.characterDetails.getOrAwaitValue(),actualResponse)
        }
    }
}