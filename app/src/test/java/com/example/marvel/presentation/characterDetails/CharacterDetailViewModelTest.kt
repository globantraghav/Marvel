package com.example.marvel.presentation.characterDetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvel.MainCoroutineRule
import com.example.marvel.common.Constants
import com.example.marvel.common.Resource
import com.example.marvel.domain.model.ModelCharacterDetail
import com.example.marvel.domain.useCases.GetCharacterDetailsUseCase
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
    private lateinit var useCase: GetCharacterDetailsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = CharacterDetailViewModel(useCase)
    }

    @Test
    fun testCharactersDetailsForEqualResponse() {
        val modelCharacterDetailMock = ModelCharacterDetail(
            Constants.ID_1,
            Constants.Iron_Man,
            Constants.Best_Avenger,
            Constants.Iron_Man_Image,
            Constants.IMG_JPG
        )

        runTest {
            val actualResponse = CharacterDetailData(
                isLoading = false,
                modelCharacterDetails = modelCharacterDetailMock,
                error = Constants.Empty_String
            )
            coEvery { useCase.invoke(Constants.ID_1) } returns flowOf(Resource.Success(modelCharacterDetailMock))
            viewModel.getCharacterDetails(Constants.ID_1)
            Assert.assertEquals(viewModel.characterDetails.getOrAwaitValue(), actualResponse)
        }
    }

    @Test
    fun testCharactersDetailsForNonEqualResponse() {
        val modelCharacterDetailMock = ModelCharacterDetail(
            Constants.ID_1,
            Constants.Iron_Man,
            Constants.Best_Avenger,
            Constants.Iron_Man_Image,
            Constants.IMG_JPG
        )
        val modelCharacterDetailActual = ModelCharacterDetail(
            Constants.ID_2,
            Constants.Captain,
            Constants.First_Avenger,
            Constants.Captain_America_Image,
            Constants.IMG_JPG
        )

        runTest {
            val actualResponse = CharacterDetailData(
                isLoading = false,
                modelCharacterDetails = modelCharacterDetailActual,
                error = Constants.Empty_String
            )
            coEvery { useCase.invoke(Constants.ID_1) } returns flowOf(Resource.Success(modelCharacterDetailMock))
            viewModel.getCharacterDetails(Constants.ID_1)
            Assert.assertNotEquals(viewModel.characterDetails.getOrAwaitValue(), actualResponse)
        }
    }

    @Test
    fun testCharactersDetailsForErrorResponse() {

        runTest {
            val actualResponse = CharacterDetailData(
                isLoading = false,
                modelCharacterDetails = null,
                error = Constants.Non_Empty_Character_Details
            )
            coEvery { useCase.invoke(Constants.ID_1) } returns flowOf(Resource.Error(Constants.Non_Empty_Character_Details))
            viewModel.getCharacterDetails(Constants.ID_1)
            Assert.assertEquals(viewModel.characterDetails.getOrAwaitValue(), actualResponse)
        }
    }
}