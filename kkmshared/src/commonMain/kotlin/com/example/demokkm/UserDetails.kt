package com.example.demokkm

import com.example.demokkm.model.StudentListResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*


class UserDetails {

    companion object {
        const val STUDENT_DETAILS_API = "https://jsonkeeper.com/b/RJWD"
        const val STUDENT_LIST_API = "https://jsonkeeper.com/b/LOAL"
    }

    private val client = HttpClient()

    fun isValidDetails(userName: String, password: String): Boolean {
        return userName.isNotEmpty() && password.isNotEmpty()
    }

    suspend fun fetchStudentList(): String {

        val response = client.get(STUDENT_LIST_API)
        return response.bodyAsText()
    }

    suspend fun fetchStudentListAsModels(): Array<StudentListResponse> {
        val response = client.get(STUDENT_LIST_API)
        return response.body()
    }

    suspend fun fetchStudentListAsModel(): StudentListResponse {

        val myClient = Platform().httpClient{}
        val response = myClient.get(STUDENT_DETAILS_API) {

        }
        return response.body()
    }

    suspend fun fetchStudentList2(): String {
        return "Response for sample call"
    }

}