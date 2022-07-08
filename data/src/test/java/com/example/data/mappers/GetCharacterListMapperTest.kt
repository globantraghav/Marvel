package com.example.data.mappers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.Constant
import com.example.data.remote.dto.characterList.Result
import com.example.data.remote.dto.characterList.Thumbnail
import io.mockk.MockKAnnotations
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetCharacterListMapperTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun testCharacterListMapperId() {
        val mockResult: List<Result> = listOf(
            Result(
                Constant.Best_Avenger,
                Constant.ID_1,
                Constant.Iron_Man,
                Thumbnail(Constant.IMG_JPG, Constant.Iron_Man_Image)
            )
        )
        val mapperResult = mockResult.toDomain()
        Assert.assertEquals(mapperResult.first().id, Constant.ID_1)
    }

    @Test
    fun testCharacterListMapperName() {
        val mockResult: List<Result> = listOf(
            Result(
                Constant.Best_Avenger,
                Constant.ID_1,
                Constant.Iron_Man,
                Thumbnail(Constant.IMG_JPG, Constant.Iron_Man_Image)
            )
        )
        val mapperResult = mockResult.toDomain()
        Assert.assertEquals(mapperResult.first().name, Constant.Iron_Man)
    }

    @Test
    fun testCharacterListMapperDescription() {
        val mockResult: List<Result> = listOf(
            Result(
                Constant.Best_Avenger,
                Constant.ID_1,
                Constant.Iron_Man,
                Thumbnail(Constant.IMG_JPG, Constant.Iron_Man_Image)
            )
        )
        val mapperResult = mockResult.toDomain()
        Assert.assertEquals(mapperResult.first().description, Constant.Best_Avenger)
    }

    @Test
    fun testCharacterListMapperThumbnailPath() {
        val mockResult: List<Result> = listOf(
            Result(
                Constant.Best_Avenger,
                Constant.ID_1,
                Constant.Iron_Man,
                Thumbnail(Constant.IMG_JPG, Constant.Iron_Man_Image)
            )
        )
        val mapperResult = mockResult.toDomain()
        Assert.assertEquals(mapperResult.first().thumbnail, Constant.Iron_Man_Image)
    }

    @Test
    fun testCharacterListMapperThumbnailExtension() {
        val mockResult: List<Result> = listOf(
            Result(
                Constant.Best_Avenger,
                Constant.ID_1,
                Constant.Iron_Man,
                Thumbnail(Constant.IMG_JPG, Constant.Iron_Man_Image)
            )
        )
        val mapperResult = mockResult.toDomain()
        Assert.assertEquals(mapperResult.first().thumbnailExt, Constant.IMG_JPG)
    }
}