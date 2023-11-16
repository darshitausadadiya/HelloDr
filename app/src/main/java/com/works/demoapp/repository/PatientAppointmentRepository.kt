package com.works.demoapp.repository

import androidx.lifecycle.LiveData
import com.works.demoapp.data.PatientAppointmentDao
import com.works.demoapp.patient.models.PatientAppointmentData

// User Repository abstracts access to multiple data sources. However this is not the part of the Architecture Component libraries.

class PatientAppointmentRepository(private val patientAppointmentDao: PatientAppointmentDao) {
    val readAllData: LiveData<List<PatientAppointmentData>> = patientAppointmentDao.readAllData()

     fun addUser(user: PatientAppointmentData) {
         patientAppointmentDao.addUser(user)
    }

     fun updateUser(user: PatientAppointmentData) {
         patientAppointmentDao.updateUser(user)
    }

     fun deleteUser(user: PatientAppointmentData) {
         patientAppointmentDao.deleteUser(user)
    }
    fun getAppointment(
        email: String?,

    ): LiveData<List<PatientAppointmentData>> {
        return patientAppointmentDao.getAppointment(email)
    }


}