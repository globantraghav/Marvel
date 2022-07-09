package com.example.domain.useCases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.Constant
import com.example.common.Resource
import com.example.domain.MainCoroutineRule
import com.example.domain.model.ModelCharacterDetail
import com.example.domain.repository.MarvelRepo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Assert
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
        runTest {
            useCase.invoke(Constant.paginatedValue)
            Mockito.verify(repository.getCharactersDetails(Constant.paginatedValue))
        }
    }

    @Test
    fun testCharacterDetailsId() {

        runTest {
            val actualResponse =
                ModelCharacterDetail(
                    Constant.ID_1,
                    Constant.Iron_Man,
                    Constant.Best_Avenger,
                    Constant.Iron_Man_Image,
                    Constant.IMG_JPG
                )

            coEvery { repository.getCharactersDetails(Constant.ID_1) } returns actualResponse

            val flowValue: Resource<ModelCharacterDetail> =
                useCase.invoke(Constant.ID_1).last()
            val mockResponse: ModelCharacterDetail? = flowValue.data
            Assert.assertEquals(mockResponse?.id, actualResponse.id)
        }
    }

    @Test
    fun testCharacterDetailsName() {

        runTest {
            val actualResponse =
                ModelCharacterDetail(
                    Constant.ID_1,
                    Constant.Iron_Man,
                    Constant.Best_Avenger,
                    Constant.Iron_Man_Image,
                    Constant.IMG_JPG
                )

            coEvery { repository.getCharactersDetails(Constant.ID_1) } returns actualResponse

            val flowValue: Resource<ModelCharacterDetail> =
                useCase.invoke(Constant.ID_1).last()
            val mockResponse: ModelCharacterDetail? = flowValue.data
            Assert.assertEquals(mockResponse?.name, actualResponse.name)
        }
    }

    @Test
    fun testCharacterDetailsDescription() {

        runTest {
            val actualResponse =
                ModelCharacterDetail(
                    Constant.ID_1,
                    Constant.Iron_Man,
                    Constant.Best_Avenger,
                    Constant.Iron_Man_Image,
                    Constant.IMG_JPG
                )

            coEvery { repository.getCharactersDetails(Constant.ID_1) } returns actualResponse

            val flowValue: Resource<ModelCharacterDetail> =
                useCase.invoke(Constant.ID_1).last()
            val mockResponse: ModelCharacterDetail? = flowValue.data
            Assert.assertEquals(mockResponse?.description, actualResponse.description)
        }
    }

    @Test
    fun testCharacterDetailsThumbnail() {

        runTest {
            val actualResponse =
                ModelCharacterDetail(
                    Constant.ID_1,
                    Constant.Iron_Man,
                    Constant.Best_Avenger,
                    Constant.Iron_Man_Image,
                    Constant.IMG_JPG
                )

            coEvery { repository.getCharactersDetails(Constant.ID_1) } returns actualResponse

            val flowValue: Resource<ModelCharacterDetail> =
                useCase.invoke(Constant.ID_1).last()
            val mockResponse: ModelCharacterDetail? = flowValue.data
            Assert.assertEquals(mockResponse?.thumbnail, actualResponse.thumbnail)
        }
    }

    @Test
    fun testCharacterDetailsThumbnailExt() {

        runTest {
            val actualResponse =
                ModelCharacterDetail(
                    Constant.ID_1,
                    Constant.Iron_Man,
                    Constant.Best_Avenger,
                    Constant.Iron_Man_Image,
                    Constant.IMG_JPG
                )

            coEvery { repository.getCharactersDetails(Constant.ID_1) } returns actualResponse

            val flowValue: Resource<ModelCharacterDetail> =
                useCase.invoke(Constant.ID_1).last()
            val mockResponse: ModelCharacterDetail? = flowValue.data
            Assert.assertEquals(mockResponse?.thumbnailExt, actualResponse.thumbnailExt)
        }
    }
}