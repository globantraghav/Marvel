package com.example.marvel.presentation.characterDetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.Constant
import com.example.common.Resource
import com.example.domain.model.ModelCharacterDetail
import com.example.domain.useCases.GetCharacterDetailsUseCase
import com.example.marvel.MainCoroutineRule
import com.example.marvel.getOrAwaitValue
import com.example.marvel.presentation.characterList.CharacterListViewModelTest.Companion.Captain
import com.example.marvel.presentation.characterList.CharacterListViewModelTest.Companion.Captain_America_Image
import com.example.marvel.presentation.characterList.CharacterListViewModelTest.Companion.First_Avenger
import com.example.marvel.presentation.characterList.CharacterListViewModelTest.Companion.ID_2
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
    fun `testCharactersDetailsForEqualResponse`() =

        runTest {

            val modelCharacterDetailMock = ModelCharacterDetail(
                Constant.ID_1,
                Constant.Iron_Man,
                Constant.Best_Avenger,
                Constant.Iron_Man_Image,
                Constant.IMG_JPG
            )

            val actualResponse = CharacterDetailData(
                isLoading = false,
                modelCharacterDetails = modelCharacterDetailMock,
                error = Constant.Empty_String
            )
            coEvery { useCase.invoke(Constant.ID_1) } returns flowOf(Resource.Success(modelCharacterDetailMock))
            viewModel.getCharacterDetails(Constant.ID_1)
            Assert.assertEquals(viewModel.characterDetails.getOrAwaitValue(), actualResponse)
        }


    @Test
    fun `testCharactersDetailsForNonEqualResponse`()=

        runTest {

            val modelCharacterDetailMock = ModelCharacterDetail(
                Constant.ID_1,
                Constant.Iron_Man,
                Constant.Best_Avenger,
                Constant.Iron_Man_Image,
                Constant.IMG_JPG
            )
            val modelCharacterDetailActual = ModelCharacterDetail(
                ID_2,
                Captain,
                First_Avenger,
                Captain_America_Image,
                Constant.IMG_JPG
            )
            val actualResponse = CharacterDetailData(
                isLoading = false,
                modelCharacterDetails = modelCharacterDetailActual,
                error = Constant.Empty_String
            )
            coEvery { useCase.invoke(Constant.ID_1) } returns flowOf(Resource.Success(modelCharacterDetailMock))
            viewModel.getCharacterDetails(Constant.ID_1)
            Assert.assertNotEquals(viewModel.characterDetails.getOrAwaitValue(), actualResponse)
        }

    @Test
    fun `testCharactersDetailsForErrorResponse`()=

        runTest {
            val actualResponse = CharacterDetailData(
                isLoading = false,
                modelCharacterDetails = null,
                error = Non_Empty_Character_Details
            )
            coEvery { useCase.invoke(Constant.ID_1) } returns flowOf(Resource.Error(Non_Empty_Character_Details))
            viewModel.getCharacterDetails(Constant.ID_1)
            Assert.assertEquals(viewModel.characterDetails.getOrAwaitValue(), actualResponse)
        }

    companion object{
        const val Non_Empty_Character_Details = "Character Details cannot be empty"
    }
}