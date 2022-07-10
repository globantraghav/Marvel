package com.example.domain.useCases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.Constant
import com.example.common.Resource
import com.example.domain.MainCoroutineRule
import com.example.domain.model.ModelCharacter
import com.example.domain.repository.MarvelRepo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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
    fun `testGetCharacterListsUseCaseCalledOrNot`()=
        runTest {
            useCase.invoke(Constant.paginatedValue)
            coVerify {
                repository.getCharactersList(Constant.paginatedValue)
            }
        }

    @Test
    fun `testGetCharacterListSize`()=

        runTest {
            val actualResponse: List<ModelCharacter> = listOf(
                ModelCharacter(
                    Constant.ID_1,
                    Constant.Iron_Man,
                    Constant.Best_Avenger,
                    Constant.Iron_Man_Image,
                    Constant.IMG_JPG
                )
            )
            coEvery { repository.getCharactersList(Constant.paginatedValue) } returns actualResponse

            val flowValue: Resource<List<ModelCharacter>> =
                useCase.invoke(Constant.paginatedValue).last()
            val mockResponse: List<ModelCharacter>? = flowValue.data
            Assert.assertEquals(mockResponse?.size, actualResponse.size)
        }

    @Test
    fun `testGetCharacterListId`() =
        runTest {

            val actualResponse: List<ModelCharacter> = listOf(
                ModelCharacter(
                    Constant.ID_1,
                    Constant.Iron_Man,
                    Constant.Best_Avenger,
                    Constant.Iron_Man_Image,
                    Constant.IMG_JPG
                )
            )
            coEvery { repository.getCharactersList(Constant.paginatedValue) } returns actualResponse

            val flowValue: Resource<List<ModelCharacter>> =
                useCase.invoke(Constant.paginatedValue).last()
            val mockResponse: List<ModelCharacter>? = flowValue.data
            Assert.assertEquals(mockResponse?.first()?.id, actualResponse.first().id)
        }

    @Test
    fun `testGetCharacterListName`() =

        runTest {

            val actualResponse: List<ModelCharacter> = listOf(
                ModelCharacter(
                    Constant.ID_1,
                    Constant.Iron_Man,
                    Constant.Best_Avenger,
                    Constant.Iron_Man_Image,
                    Constant.IMG_JPG
                )
            )
            coEvery { repository.getCharactersList(Constant.paginatedValue) } returns actualResponse

            val flowValue: Resource<List<ModelCharacter>> =
                useCase.invoke(Constant.paginatedValue).last()
            val mockResponse: List<ModelCharacter>? = flowValue.data
            Assert.assertEquals(mockResponse?.first()?.name, actualResponse.first().name)
        }

    @Test
    fun `testGetCharacterListDescription`()=

        runTest {

            val actualResponse: List<ModelCharacter> = listOf(
                ModelCharacter(
                    Constant.ID_1,
                    Constant.Iron_Man,
                    Constant.Best_Avenger,
                    Constant.Iron_Man_Image,
                    Constant.IMG_JPG
                )
            )
            coEvery { repository.getCharactersList(Constant.paginatedValue) } returns actualResponse

            val flowValue: Resource<List<ModelCharacter>> =
                useCase.invoke(Constant.paginatedValue).last()
            val mockResponse: List<ModelCharacter>? = flowValue.data
            Assert.assertEquals(
                mockResponse?.first()?.description,
                actualResponse.first().description
            )
        }

    @Test
    fun `testGetCharacterListThumbnail`() =

        runTest {

            val actualResponse: List<ModelCharacter> = listOf(
                ModelCharacter(
                    Constant.ID_1,
                    Constant.Iron_Man,
                    Constant.Best_Avenger,
                    Constant.Iron_Man_Image,
                    Constant.IMG_JPG
                )
            )
            coEvery { repository.getCharactersList(Constant.paginatedValue) } returns actualResponse

            val flowValue: Resource<List<ModelCharacter>> =
                useCase.invoke(Constant.paginatedValue).last()
            val mockResponse: List<ModelCharacter>? = flowValue.data
            Assert.assertEquals(mockResponse?.first()?.thumbnail, actualResponse.first().thumbnail)
        }

    @Test
    fun `testGetCharacterListThumbnailExt`()=

        runTest {

            val actualResponse: List<ModelCharacter> = listOf(
                ModelCharacter(
                    Constant.ID_1,
                    Constant.Iron_Man,
                    Constant.Best_Avenger,
                    Constant.Iron_Man_Image,
                    Constant.IMG_JPG
                )
            )
            coEvery { repository.getCharactersList(Constant.paginatedValue) } returns actualResponse

            val flowValue: Resource<List<ModelCharacter>> =
                useCase.invoke(Constant.paginatedValue).last()
            val mockResponse: List<ModelCharacter>? = flowValue.data
            Assert.assertEquals(
                mockResponse?.first()?.thumbnailExt,
                actualResponse.first().thumbnailExt
            )
        }
}