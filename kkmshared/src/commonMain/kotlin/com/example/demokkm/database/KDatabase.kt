package com.example.demokkm.database

import com.example.demokkm.model.StudentDetails
import comexampledemokkmdatabase.StudentListResponse

class KDatabase(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.mySchemeDatabaseQueries

    fun getAllStudentList(): List<StudentListResponse> {
        return dbQuery.selectAll().executeAsList()
    }

    fun insertStudentItem(model: StudentDetails) {
        return dbQuery.insertItem(model.id?.toLong(), model.name, model.age?.toLong(), model.college_name)
    }

    fun insertStudentList(studentList: List<StudentDetails>) {
        dbQuery.transaction {
            studentList.forEach { student ->
                dbQuery.insertItem(student.id?.toLong(), student.name, student.age?.toLong(), student.college_name)
            }
        }
    }

    fun getAllStudents(): List<StudentDetails> {
        return dbQuery.selectAll(::mapStudentDetails).executeAsList()
    }

    private fun mapStudentDetails(
        id: Long?,
        name: String?,
        age: Long?,
        college_name: String?
    ): StudentDetails {
        return StudentDetails(
            id?.toInt(), name, age?.toInt(), college_name
        )
    }
}