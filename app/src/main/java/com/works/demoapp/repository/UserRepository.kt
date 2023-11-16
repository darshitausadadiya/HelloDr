package com.works.demoapp.repository

import androidx.lifecycle.LiveData
import com.works.demoapp.data.UserDao

import com.works.demoapp.patient.models.PatientData

// User Repository abstracts access to multiple data sources. However this is not the part of the Architecture Component libraries.

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<PatientData>> = userDao.readAllData()

     fun addUser(user: PatientData) {
        userDao.addUser(user)
    }

     fun updateUser(user: PatientData) {
        userDao.updateUser(user)
    }

     fun deleteUser(user: PatientData) {
        userDao.deleteUser(user)
    }
    fun getUsername(
        userName: String,
        password: String,
        client: String
    ): LiveData<List<PatientData>> {
        return userDao.getUsername(userName, password, client)
    }
    fun getDoctorList(client: String,field: String): LiveData<List<PatientData>> {
        return userDao.getDoctorList( client,field)
    }


}