package com.example.demokkm.model

import kotlinx.serialization.Serializable

@Serializable
data class StudentDetails(
    var id: Int? = null,
    var name: String? = null,
    var age: Int? = null,
    var college_name: String? = null
)