package com.example.common

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constant {

    companion object {
        const val BASE_URL = "https://gateway.marvel.com/"
        val timestamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = ""
        const val PRIVATE_KEY = ""
        fun hash(): String {
            val input = "$timestamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }

        const val Iron_Man = "Iron Man"
        const val Best_Avenger = "Best Avenger"
        const val Captain = "Captain America"
        const val First_Avenger = "First Avenger"
        const val Non_Empty_List = "Character list cannot be empty"
        const val paginatedValue = 20
        const val Non_Empty_Character_Details = "Character Details cannot be empty"
        const val DELAY: Long = 1000
        const val IMG_JPG = ".jpg"
        const val Iron_Man_Image = "iron_man.jpg"
        const val Captain_America_Image = "captain_america.jpg"
        const val Error = "Error is"
        const val Unexpected_Error = "Unexpected Error"
        const val Empty_String = ""
        const val ID_1 = 1
        const val ID_2 = 2
        const val ZERO = 0
        const val HTTP = "http"
        const val HTTPS = "https"
        const val IMAGE_EXTENSION = "/portrait_xlarge."

    }
}