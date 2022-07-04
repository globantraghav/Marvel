package com.example.marvel.common

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {
    companion object{
        const val BASE_URL = "https://gateway.marvel.com/"
        val timestamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = "50a2f817647f6f01f3d8ffd926f7025e"
        const val PRIVATE_KEY = "66c82a2bd284cdfbd84102638eaa8a4f2e0382fc"
        fun hash():String{
            val input = "$timestamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
        }
    }

}