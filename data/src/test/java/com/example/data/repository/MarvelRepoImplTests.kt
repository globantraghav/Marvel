package com.example.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.Constant
import com.example.data.MainCoroutineRule
import com.example.data.remote.NetworkApi
import com.example.data.remote.dto.characterDetail.CharacterDetailDto
import com.example.data.remote.dto.characterList.CharacterListDto
import com.example.data.remote.dto.characterList.Data
import com.example.data.remote.dto.characterList.Result
import com.example.data.remote.dto.characterList.Thumbnail
import com.example.data.remote.utils.SafeApiRequest
import com.example.domain.model.ModelCharacter
import com.example.domain.model.ModelCharacterDetail
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MarvelRepoImplTests {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var repoImpl: MarvelRepoImpl

    @MockK
    private lateinit var networkApi: NetworkApi

    @MockK
    private lateinit var safeApiRequest: SafeApiRequest

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        repoImpl = MarvelRepoImpl(networkApi)
    }

    @Test
    fun `testCharacterListCalled`() =
        runTest {
            repoImpl.getCharactersList(Constant.paginatedValue)
            coVerify {
                networkApi.getCharactersList(offset = Constant.paginatedValue.toString())
            }
        }

    @Test
    fun `testCharacterDetailCalled`() =
        runTest {
            repoImpl.getCharactersDetails(Constant.ID_1)
            coVerify {
                networkApi.getCharactersDetails(characterId = Constant.ID_1)
            }
        }

    @Test
    fun `testCharacterListRepoDescription`() =
        runTest {

            val actualResponse = CharacterListDto(
                Data(
                    listOf(
                        Result(
                            Constant.Best_Avenger,
                            Constant.ID_1,
                            Constant.Iron_Man,
                            Thumbnail(Constant.IMG_JPG, Constant.Iron_Man_Image)
                        )
                    )
                )
            )
            coEvery { safeApiRequest.safeApiRequest { networkApi.getCharactersList(offset = Constant.paginatedValue.toString()) } } returns actualResponse

            val mockResponse: ModelCharacter =
                repoImpl.getCharactersList(offset = Constant.paginatedValue).first()

            Assert.assertEquals(
                mockResponse.description,
                actualResponse.data.results.first().description
            )
        }

    @Test
    fun `testCharacterListRepoName`() =
        runTest {

            val actualResponse = CharacterListDto(
                Data(
                    listOf(
                        Result(
                            Constant.Best_Avenger,
                            Constant.ID_1,
                            Constant.Iron_Man,
                            Thumbnail(Constant.IMG_JPG, Constant.Iron_Man_Image)
                        )
                    )
                )
            )
            coEvery { safeApiRequest.safeApiRequest { networkApi.getCharactersList(offset = Constant.paginatedValue.toString()) } } returns actualResponse

            val mockResponse: ModelCharacter =
                repoImpl.getCharactersList(offset = Constant.paginatedValue).first()

            Assert.assertEquals(
                mockResponse.name,
                actualResponse.data.results.first().name
            )
        }

    @Test
    fun `testCharacterListRepoId`() =
        runTest {

            val actualResponse = CharacterListDto(
                Data(
                    listOf(
                        Result(
                            Constant.Best_Avenger,
                            Constant.ID_1,
                            Constant.Iron_Man,
                            Thumbnail(Constant.IMG_JPG, Constant.Iron_Man_Image)
                        )
                    )
                )
            )
            coEvery { safeApiRequest.safeApiRequest { networkApi.getCharactersList(offset = Constant.paginatedValue.toString()) } } returns actualResponse

            val mockResponse: ModelCharacter =
                repoImpl.getCharactersList(offset = Constant.paginatedValue).first()

            Assert.assertEquals(
                mockResponse.id,
                actualResponse.data.results.first().id
            )
        }

    @Test
    fun `testCharacterListRepoThumbnail`() =
        runTest {

            val actualResponse = CharacterListDto(
                Data(
                    listOf(
                        Result(
                            Constant.Best_Avenger,
                            Constant.ID_1,
                            Constant.Iron_Man,
                            Thumbnail(Constant.IMG_JPG, Constant.Iron_Man_Image)
                        )
                    )
                )
            )
            coEvery { safeApiRequest.safeApiRequest { networkApi.getCharactersList(offset = Constant.paginatedValue.toString()) } } returns actualResponse

            val mockResponse: ModelCharacter =
                repoImpl.getCharactersList(offset = Constant.paginatedValue).first()

            Assert.assertEquals(
                mockResponse.thumbnail,
                actualResponse.data.results.first().thumbnail.path
            )
        }

    @Test
    fun `testCharacterListRepoExt`() =
        runTest {

            val actualResponse = CharacterListDto(
                Data(
                    listOf(
                        Result(
                            Constant.Best_Avenger,
                            Constant.ID_1,
                            Constant.Iron_Man,
                            Thumbnail(Constant.IMG_JPG, Constant.Iron_Man_Image)
                        )
                    )
                )
            )
            coEvery { safeApiRequest.safeApiRequest { networkApi.getCharactersList(offset = Constant.paginatedValue.toString()) } } returns actualResponse

            val mockResponse: ModelCharacter =
                repoImpl.getCharactersList(offset = Constant.paginatedValue).first()

            Assert.assertEquals(
                mockResponse.thumbnailExt,
                actualResponse.data.results.first().thumbnail.extension
            )
        }

    @Test
    fun `testCharacterDetailsRepoId`() =
        runTest {

            val actualResponse = CharacterDetailDto(
                com.example.data.remote.dto.characterDetail.Data(
                    listOf(
                        com.example.data.remote.dto.characterDetail.Result(
                            Constant.Best_Avenger,
                            Constant.ID_1,
                            Constant.Iron_Man,
                            com.example.data.remote.dto.characterDetail.Thumbnail(
                                Constant.IMG_JPG,
                                Constant.Iron_Man_Image
                            )
                        )
                    )
                )
            )
            coEvery { safeApiRequest.safeApiRequest { networkApi.getCharactersDetails(characterId = Constant.ID_1) } } returns actualResponse

            val mockResponse: ModelCharacterDetail =
                repoImpl.getCharactersDetails(charId = Constant.ID_1)

            Assert.assertEquals(
                mockResponse.id,
                actualResponse.data.results.first().id
            )
        }

    @Test
    fun `testCharacterDetailsRepoName`() =
        runTest {

            val actualResponse = CharacterDetailDto(
                com.example.data.remote.dto.characterDetail.Data(
                    listOf(
                        com.example.data.remote.dto.characterDetail.Result(
                            Constant.Best_Avenger,
                            Constant.ID_1,
                            Constant.Iron_Man,
                            com.example.data.remote.dto.characterDetail.Thumbnail(
                                Constant.IMG_JPG,
                                Constant.Iron_Man_Image
                            )
                        )
                    )
                )
            )
            coEvery { safeApiRequest.safeApiRequest { networkApi.getCharactersDetails(characterId = Constant.ID_1) } } returns actualResponse

            val mockResponse: ModelCharacterDetail =
                repoImpl.getCharactersDetails(charId = Constant.ID_1)

            Assert.assertEquals(
                mockResponse.name,
                actualResponse.data.results.first().name
            )
        }

    @Test
    fun `testCharacterDetailsRepoDescription`() =
        runTest {

            val actualResponse = CharacterDetailDto(
                com.example.data.remote.dto.characterDetail.Data(
                    listOf(
                        com.example.data.remote.dto.characterDetail.Result(
                            Constant.Best_Avenger,
                            Constant.ID_1,
                            Constant.Iron_Man,
                            com.example.data.remote.dto.characterDetail.Thumbnail(
                                Constant.IMG_JPG,
                                Constant.Iron_Man_Image
                            )
                        )
                    )
                )
            )
            coEvery { safeApiRequest.safeApiRequest { networkApi.getCharactersDetails(characterId = Constant.ID_1) } } returns actualResponse

            val mockResponse: ModelCharacterDetail =
                repoImpl.getCharactersDetails(charId = Constant.ID_1)

            Assert.assertEquals(
                mockResponse.description,
                actualResponse.data.results.first().description
            )
        }

    @Test
    fun `testCharacterDetailsRepoThumbnail`() =
        runTest {

            val actualResponse = CharacterDetailDto(
                com.example.data.remote.dto.characterDetail.Data(
                    listOf(
                        com.example.data.remote.dto.characterDetail.Result(
                            Constant.Best_Avenger,
                            Constant.ID_1,
                            Constant.Iron_Man,
                            com.example.data.remote.dto.characterDetail.Thumbnail(
                                Constant.IMG_JPG,
                                Constant.Iron_Man_Image
                            )
                        )
                    )
                )
            )
            coEvery { safeApiRequest.safeApiRequest { networkApi.getCharactersDetails(characterId = Constant.ID_1) } } returns actualResponse

            val mockResponse: ModelCharacterDetail =
                repoImpl.getCharactersDetails(charId = Constant.ID_1)

            Assert.assertEquals(
                mockResponse.thumbnail,
                actualResponse.data.results.first().thumbnail.path
            )
        }

    @Test
    fun `testCharacterDetailsRepoThumbnailExt`() =
        runTest {

            val actualResponse = CharacterDetailDto(
                com.example.data.remote.dto.characterDetail.Data(
                    listOf(
                        com.example.data.remote.dto.characterDetail.Result(
                            Constant.Best_Avenger,
                            Constant.ID_1,
                            Constant.Iron_Man,
                            com.example.data.remote.dto.characterDetail.Thumbnail(
                                Constant.IMG_JPG,
                                Constant.Iron_Man_Image
                            )
                        )
                    )
                )
            )
            coEvery { safeApiRequest.safeApiRequest { networkApi.getCharactersDetails(characterId = Constant.ID_1) } } returns actualResponse

            val mockResponse: ModelCharacterDetail =
                repoImpl.getCharactersDetails(charId = Constant.ID_1)

            Assert.assertEquals(
                mockResponse.thumbnailExt,
                actualResponse.data.results.first().thumbnail.extension
            )
        }
}