package com.example.data.mappers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.Constant
import com.example.data.remote.dto.characterDetail.Result
import com.example.data.remote.dto.characterDetail.Thumbnail
import io.mockk.MockKAnnotations
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetCharacterDetailsMapperTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun testCharacterDetailsMapperDescription() {
        val mockResult =
            Result(
                Constant.Best_Avenger,
                Constant.ID_1,
                Constant.Iron_Man,
                Thumbnail(Constant.IMG_JPG, Constant.Iron_Man_Image)
            )

        val mapperResult = mockResult.toDomain()
        Assert.assertEquals(mapperResult.description, Constant.Best_Avenger)
    }

    @Test
    fun testCharacterDetailsMapperId() {
        val mockResult =
            Result(
                Constant.Best_Avenger,
                Constant.ID_1,
                Constant.Iron_Man,
                Thumbnail(Constant.IMG_JPG, Constant.Iron_Man_Image)
            )

        val mapperResult = mockResult.toDomain()
        Assert.assertEquals(mapperResult.id, Constant.ID_1)
    }

    @Test
    fun testCharacterDetailsMapperName() {
        val mockResult =
            Result(
                Constant.Best_Avenger,
                Constant.ID_1,
                Constant.Iron_Man,
                Thumbnail(Constant.IMG_JPG, Constant.Iron_Man_Image)
            )
        val mapperResult = mockResult.toDomain()
        Assert.assertEquals(mapperResult.name, Constant.Iron_Man)
    }

    @Test
    fun testCharacterDetailsMapperThumbnailPath() {
        val mockResult =
            Result(
                Constant.Best_Avenger,
                Constant.ID_1,
                Constant.Iron_Man,
                Thumbnail(Constant.IMG_JPG, Constant.Iron_Man_Image)
            )
        val mapperResult = mockResult.toDomain()
        Assert.assertEquals(mapperResult.thumbnail, Constant.Iron_Man_Image)
    }

    @Test
    fun testCharacterDetailsMapperThumbnailExtension() {
        val mockResult =
            Result(
                Constant.Best_Avenger,
                Constant.ID_1,
                Constant.Iron_Man,
                Thumbnail(Constant.IMG_JPG, Constant.Iron_Man_Image)
            )
        val mapperResult = mockResult.toDomain()
        Assert.assertEquals(mapperResult.thumbnailExt, Constant.IMG_JPG)
    }
}