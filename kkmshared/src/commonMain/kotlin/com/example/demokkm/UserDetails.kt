package com.example.demokkm

import com.example.demokkm.model.BaseResponseTestModel
import com.example.demokkm.model.StudentDetails
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*


class UserDetails {

    companion object {
        const val STUDENT_DETAILS_API = "https://jsonkeeper.com/b/RJWD"
        const val STUDENT_LIST_API = "https://jsonkeeper.com/b/LOAL"
        const val BASE_RESPONSE_TEST_API = "https://jsonkeeper.com/b/E2GG"
        const val BASE_RESPONSE_TEST_API_2 = "https://jsonkeeper.com/b/93HN"
    }

    private val client = HttpClient()

    fun isValidDetails(userName: String, password: String): Boolean {
        return userName.isNotEmpty() && password.isNotEmpty()
    }

    suspend fun fetchStudentList(): String {

        val response = client.get(STUDENT_LIST_API)
        return response.bodyAsText()
    }

    suspend fun fetchStudentListAsModels(): Array<StudentDetails> {
        val response = client.get(STUDENT_LIST_API)
        return response.body()
    }

    suspend fun fetchStudentListAsModel(): StudentDetails {

        val myClient = Platform().httpClient{}
        val response = myClient.get(STUDENT_DETAILS_API) {

        }
        return response.body()
    }

    suspend fun fetchStudentList2(): String {
        return "Response for sample call"
    }

    suspend fun fetchBaseResponseModel(): BaseResponseTestModel {
        val myClient = Platform().httpClient{}
        val response = myClient.get(BASE_RESPONSE_TEST_API) {

        }
        return response.body()
    }

}