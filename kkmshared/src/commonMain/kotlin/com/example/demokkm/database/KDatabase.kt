package com.example.demokkm.database

import com.example.demokkm.AppLogger
import com.example.demokkm.model.StudentDetails
import comexampledemokkmdatabase.StudentListResponse

class KDatabase(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.mySchemeDatabaseQueries

    private val appLogger = AppLogger()

    fun getAllStudentList(): List<StudentListResponse> {
        val result = dbQuery.selectAll().executeAsList()
        appLogger.d("Database", "getAllStudentList size:${result.size} items: $result" )
        return result
    }

    fun insertStudentItem(model: StudentDetails) {
        val inserted = dbQuery.insertItem(model.id?.toLong(), model.name, model.age?.toLong(), model.college_name)
        appLogger.d("Database", "Item Inserted: $model" )
        return inserted
    }

    fun insertStudentList(studentList: List<StudentDetails>) {
        dbQuery.transaction {
            studentList.forEach { student ->
                dbQuery.insertItem(student.id?.toLong(), student.name, student.age?.toLong(), student.college_name)
            }
        }
        appLogger.d("Database", "Inserted count: ${studentList.size} items: $studentList" )
    }

    fun getAllStudents(): List<StudentDetails> {
        val studentDetailList = dbQuery.selectAll(::mapStudentDetails).executeAsList()
        appLogger.d("Database", "getAllStudents size: ${studentDetailList.size} items: $studentDetailList" )
        return studentDetailList
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