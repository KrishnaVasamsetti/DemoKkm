package com.example.demokkm.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponseTestModel(
    var statusCode: Int? = null,
    var message: String? = null,
    @SerialName("data")
    var data: List<String>? = null
)