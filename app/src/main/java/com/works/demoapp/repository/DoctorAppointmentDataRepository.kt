package com.works.demoapp.repository

import androidx.lifecycle.LiveData
import com.works.demoapp.data.DoctorAppointmentDataDao
import com.works.demoapp.doctor.models.DoctorAppointmentData

// User Repository abstracts access to multiple data sources. However this is not the part of the Architecture Component libraries.

class DoctorAppointmentDataRepository(private val doctorAppointmentDataDao: DoctorAppointmentDataDao) {
    val readAllData: LiveData<List<DoctorAppointmentData>> = doctorAppointmentDataDao.readAllData()

     fun addUser(user: DoctorAppointmentData) {
         doctorAppointmentDataDao.addUser(user)
    }

     fun updateUser(user: DoctorAppointmentData) {
         doctorAppointmentDataDao.updateUser(user)
    }

     fun deleteUser(user: DoctorAppointmentData) {
         doctorAppointmentDataDao.deleteUser(user)
    }
    fun getAppointment(
        email: String?,

    ): LiveData<List<DoctorAppointmentData>> {
        return doctorAppointmentDataDao.getAppointment(email)
    }


}