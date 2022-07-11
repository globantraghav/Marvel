package com.example.data.remote.utils

import java.io.IOException

class Exception {
    class ApiException(message: String) : IOException(message)
}