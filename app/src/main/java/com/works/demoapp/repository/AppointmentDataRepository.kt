package com.works.demoapp.repository

import androidx.lifecycle.LiveData
import com.works.demoapp.data.AppointmentDataDao
import com.works.demoapp.doctor.models.AppointmentData

// User Repository abstracts access to multiple data sources. However this is not the part of the Architecture Component libraries.

class AppointmentDataRepository(private val appointmentDataDao: AppointmentDataDao) {
     val readAllData: LiveData<List<AppointmentData>> = appointmentDataDao.readAllData()

     fun addUser(user: AppointmentData) {
         appointmentDataDao.addUser(user)
    }

     fun updateUser(user: AppointmentData) {
         appointmentDataDao.updateUser(user)
    }

     fun deleteUser(user: AppointmentData) {
         appointmentDataDao.deleteUser(user)
    }
//    fun getUsername(
//        userName: String,
//        password: String,
//        client: String
//    ): LiveData<List<PatientAppointmentData>> {
//        return patientAppointmentDao.getUsername(userName, password, client)
//    }


}